package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/user.sql")  
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/hotel.sql")
public class HotelServiceTest extends BaseTests {
	
	@Autowired
	HotelService hotelService;
	
	@Test
	@DisplayName("Insert novo usuario")
	void insert() {	
		Hotel h = new Hotel(1, "name", "a@g.com", "1234", 1231231, "user", "passw", "desc", "cnpj", "ADMIN");
		hotelService.insert(h);
		assertEquals(4, hotelService.listAll().size());
		assertEquals(1, h.getId());
	}
	
	@Test
	@DisplayName("Update usuario")
	void update() {	
		Hotel h = new Hotel(50, "name", "a@g.com", "1234", 1231231, "user", "passw", "desc", "cnpj", "ADMIN");
		hotelService.update(h);
		assertEquals(3, hotelService.listAll().size());
	}
	
	@Test
	@DisplayName("Procura por nome")
	void searchByName() {	
		assertEquals(3, hotelService.findByNameStartsWithIgnoreCase("U").size());
		assertEquals(1, hotelService.findByNameStartsWithIgnoreCase("user 4").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByNameStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum hotel/nome registrado com : A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por usuario")
	void searchByUser() {	
		assertEquals(3, hotelService.findByUserStartsWithIgnoreCase("customer").size());
		assertEquals(1, hotelService.findByUserStartsWithIgnoreCase("customer4").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByUserStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum hotel/usuário registrado com : A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep")
	void searchByCep() {	
		assertEquals(1, hotelService.findByCepStartsWith("12345678").size());
		assertEquals(1, hotelService.findByCepStartsWith("45678901").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByCepStartsWith("9").size());
		assertEquals("Nenhum hotel/Cep registrado com : 9", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por email")
	void searchByEmail() {	
		assertEquals(3, hotelService.findByEmailStartsWithIgnoreCase("user").size());
		assertEquals(1, hotelService.findByEmailStartsWithIgnoreCase("user1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByEmailStartsWithIgnoreCase("user232").size());
		assertEquals("Nenhum hotel/Email registrado com : user232", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cnpj")
	void searchByCnpj() {	
		assertEquals(1, hotelService.findByCnpjStartsWith("1").size());
		assertEquals(1, hotelService.findByCnpjStartsWith("2").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByCnpjStartsWith("545").size());
		assertEquals("Nenhum hotel/Cnpj registrado com : 545", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por desc")
	void searchByDesc() {	
		assertEquals(3, hotelService.findByDescriptionContainingIgnoreCase("hotel").size());
		assertEquals(1, hotelService.findByDescriptionContainingIgnoreCase("1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByDescriptionContainingIgnoreCase("abc").size());
		assertEquals("Nenhuma descrição contendo : abc", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cnpj e nome")
	void searchByCnpjAndName() {	
		assertEquals(1, hotelService.findByCnpjStartsWithAndNameStartsWithIgnoreCase("1", "u").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		hotelService.findByCnpjStartsWithAndNameStartsWithIgnoreCase("1", "a").size());
		assertEquals("Nenhum hotel/cnpj e hotel/nome registrado com : 1 e a", ex.getMessage());
	}

}
