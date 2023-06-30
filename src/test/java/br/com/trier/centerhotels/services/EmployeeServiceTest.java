package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/user.sql")  
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/hotel.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/employee.sql")
class EmployeeServiceTest extends BaseTests{

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	HotelService hotelService;
	
	@Test
	@DisplayName("Insert novo usuario")
	void insert() {	
		Employee e = new Employee(1, "asdasd", "asdasda@gmail.com", "sadasd", 12312, "asdada", "asdaa", "asdada", hotelService.findById(50), "ADMIN");
		employeeService.insert(e);
		assertEquals(4, employeeService.listAll().size());
		assertEquals(1, e.getId());
	}
	
	@Test
	@DisplayName("Update usuario")
	void update() {	
		Employee e = new Employee(80, "asdasd", "asdasda@gmail.com", "sadasd", 12312, "asdada", "asdaa", "asdada", hotelService.findById(50), "ADMIN");
		employeeService.update(e);
		assertEquals(3, employeeService.listAll().size());
	}
	
	@Test
	@DisplayName("Procura por nome")
	void searchByName() {	
		assertEquals(3, employeeService.findByNameStartsWithIgnoreCase("U").size());
		assertEquals(1, employeeService.findByNameStartsWithIgnoreCase("user 1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByNameStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum funcionário/nome começa com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por usuario")
	void searchByUser() {	
		assertEquals(3, employeeService.findByUserStartsWithIgnoreCase("customer").size());
		assertEquals(1, employeeService.findByUserStartsWithIgnoreCase("customer7").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByUserStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum funcionário/usuário começa com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep")
	void searchByCep() {	
		assertEquals(1, employeeService.findByCepStartsWith("98765432").size());
		assertEquals(1, employeeService.findByCepStartsWith("65432109").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCepStartsWith("x").size());
		assertEquals("Nenhum funcionário/cep igual á : x", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por email")
	void searchByEmail() {	
		assertEquals(3, employeeService.findByEmailStartsWithIgnoreCase("user").size());
		assertEquals(1, employeeService.findByEmailStartsWithIgnoreCase("user1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByEmailStartsWithIgnoreCase("user232").size());
		assertEquals("Nenhum funcionário/email igual á : user232", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cpf")
	void searchByCpf() {	
		assertEquals(1, employeeService.findByCpfStartsWith("1").size());
		assertEquals(1, employeeService.findByCpfStartsWith("2").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCpfStartsWith("545").size());
		assertEquals("Nenhum funcionário/cpf igual á : 545", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep e nome")
	void searchByCepAndName() {	
		assertEquals(1, employeeService.findByCepStartsWithAndNameStartsWithIgnoreCase("9", "u").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCepStartsWithAndNameStartsWithIgnoreCase("1", "a").size());
		assertEquals("Nenhum funcionário/cep e funcionário/name igual á : 1 e a", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por hotel")
	void searchByHotel() {	
		Hotel hotel = new Hotel(50, null, null, null, null, null, null, null, null, null);
		assertEquals(1, employeeService.findByHotel(hotel).size());
		hotel.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByHotel(hotel).size());
		assertEquals("Nenhum funcionário no hotel igual á : Hotel(description=null, cnpj=null)", ex.getMessage());
	}

}
