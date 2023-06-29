package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/user.sql")  
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/hotel.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/employee.sql")
class EmployeeServiceTest extends BaseTests{

	@Autowired
	EmployeeService employeeService;
	
	@Test
	@DisplayName("Procura por nome")
	void searchByName() {	
		assertEquals(3, employeeService.findByNameStartsWithIgnoreCase("U").size());
		assertEquals(1, employeeService.findByNameStartsWithIgnoreCase("user 1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByNameStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum cliente/nome encontrado com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por usuario")
	void searchByUser() {	
		assertEquals(3, employeeService.findByUserStartsWithIgnoreCase("customer").size());
		assertEquals(1, employeeService.findByUserStartsWithIgnoreCase("customer1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByUserStartsWithIgnoreCase("A").size());
		assertEquals("Nenhum cliente/usuário começa com: A", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep")
	void searchByCep() {	
		assertEquals(1, employeeService.findByCepStartsWith("12345678").size());
		assertEquals(1, employeeService.findByCepStartsWith("45678901").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCepStartsWith("9").size());
		assertEquals("Nenhum cliente/cep encontrado com: 9", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por email")
	void searchByEmail() {	
		assertEquals(3, employeeService.findByEmailStartsWithIgnoreCase("user").size());
		assertEquals(1, employeeService.findByEmailStartsWithIgnoreCase("user1").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByEmailStartsWithIgnoreCase("user232").size());
		assertEquals("Nenhum cliente/email encontrado com: user232", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cpf")
	void searchByCpf() {	
		assertEquals(1, employeeService.findByCpfStartsWith("1").size());
		assertEquals(1, employeeService.findByCpfStartsWith("2").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCpfStartsWith("545").size());
		assertEquals("Nenhum cliente/cpf encontrado com: 545", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por cep e nome")
	void searchByCepAndName() {	
		assertEquals(1, employeeService.findByCepStartsWithAndNameStartsWithIgnoreCase("1", "u").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		employeeService.findByCepStartsWithAndNameStartsWithIgnoreCase("1", "a").size());
		assertEquals("Nenhum cliente/cep e cliente/name encontrado com: 1 e a", ex.getMessage());
	}

}
