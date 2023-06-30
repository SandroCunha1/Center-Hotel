package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.services.exceptions.IntegrityViolation;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;
import br.com.trier.centerhotels.models.users.Customer;


@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/user.sql")  
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/customer.sql")
@Transactional
class CustomerServiceTest extends BaseTests {
	
	@Autowired
	CustomerService customerService;

	@Test
	@DisplayName("Insert novo usuario")
	void insert() {	
		Customer customer = new Customer(1, "Sandro", "sandrocunha@gmail.com", "1234", 123213, "sandro", "123", "1231244", "ADMIN");
		customerService.insert(customer);
		assertEquals(4, customerService.listAll().size());
		assertEquals(1, customer.getId());
	}
	
	@Test
	@DisplayName("Insert novo usuario com email já cadastrado")
	void insertInvalidEmail() {	
		Customer customer = new Customer(1, "Sandro", "user1@example.com", "1234", 123213, "sandro", "123", "1231244", "ADMIN");
		var ex = assertThrows(IntegrityViolation.class, () ->
		customerService.insert(customer));
		assertEquals("Email já cadastrado", ex.getMessage());
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Insert novo usuario com usuário já cadastrado")
	void insertInvalidUser() {	
		Customer customer = new Customer(1, "Sandro", "sfsdf@example.com", "1234", 123213, "customer1", "123", "1231244", "ADMIN");
		var ex = assertThrows(IntegrityViolation.class, () ->
		customerService.insert(customer));
		assertEquals("Usuário já existe", ex.getMessage());
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Update usuario")
	void update() {	
		Customer customer = new Customer(20, "Sandro", "sandrocunha@gmail.com", "1234", 123213, "sandro", "123", "1231244", "ADMIN");
		customerService.update(customer);
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Update usuario com email já cadastrado")
	void updateInvalidEmial() {	
		Customer customer = new Customer(20, "Sandro", "user1@example.com", "1234", 123213, "customer10", "123", "1231244", "ADMIN");
		customerService.update(customer);
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Update usuario com usuário já cadastrado")
	void updateInvalidUser() {	
		Customer customer = new Customer(20, "Sandro", "sfsdf@example.com", "1234", 123213, "customer1", "123", "1231244", "ADMIN");
		customerService.update(customer);
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Buscar por ID válido")
	void searchIdValid() {
		Customer usuario = customerService.findById(20);
		assertNotNull(usuario);
		assertEquals(20, usuario.getId());	
	}
	
	@Test
	@DisplayName("Buscar por ID inválido")
	void searchIdInvalid() {	
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.delete(10));
		assertEquals("O objeto com ID 10 não existe", ex.getMessage());
	}
	
	@Test
	@DisplayName("Buscar todos")
	void searchAll() {	
		assertEquals(3, customerService.listAll().size());
	}
	
	@Test
	@DisplayName("Buscar todos com nenhum cadastro")
	@Sql({"classpath:/resources/sqls/limpa_tabelas.sql"})
	void searchAllWithNoUser() {	
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.listAll());
		assertEquals("Nenhum objeto cadastrado", ex.getMessage());
	}
	
	@Test
	@DisplayName("Delete usuário")
	void deleteUser() {	
		customerService.delete(20);
		assertEquals(2, customerService.listAll().size());
		assertEquals(30, customerService.listAll().get(0).getId());
	}
	
	@Test
	@DisplayName("Procura por nome")
	void searchByName() {	
		assertEquals(3, customerService.findByNameStartsWithIgnoreCase("user").size());
		assertEquals(1, customerService.findByNameStartsWithIgnoreCase("user 1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByNameStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum cliente/nome encontrado com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por usuario")
	void searchByUser() {	
		assertEquals(3, customerService.findByUserStartsWithIgnoreCase("customer").size());
		assertEquals(1, customerService.findByUserStartsWithIgnoreCase("customer1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByUserStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum cliente/usuário começa com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep")
	void searchByCep() {	
		assertEquals(1, customerService.findByCepStartsWith("12345678").size());
		assertEquals(1, customerService.findByCepStartsWith("45678901").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByCepStartsWith("9").size());
		assertEquals("Nenhum cliente/cep encontrado com: 9", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por email")
	void searchByEmail() {	
		assertEquals(3, customerService.findByEmailStartsWithIgnoreCase("user").size());
		assertEquals(1, customerService.findByEmailStartsWithIgnoreCase("user1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByEmailStartsWithIgnoreCase("user232").size());
		assertEquals("Nenhum cliente/email encontrado com: user232", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cpf")
	void searchByCpf() {	
		assertEquals(1, customerService.findByCpfStartsWith("1").size());
		assertEquals(1, customerService.findByCpfStartsWith("2").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByCpfStartsWith("545").size());
		assertEquals("Nenhum cliente/cpf encontrado com: 545", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep e nome")
	void searchByCepAndName() {	
		assertEquals(1, customerService.findByCepStartsWithAndNameStartsWithIgnoreCase("1", "u").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		customerService.findByCepStartsWithAndNameStartsWithIgnoreCase("1", "a").size());
		assertEquals("Nenhum cliente/cep e cliente/name encontrado com: 1 e a", ex.getMessage());
	}
	


}
