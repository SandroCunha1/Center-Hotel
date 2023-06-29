package br.com.trier.centerhotels;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import br.com.trier.centerhotels.services.CustomerService;
import br.com.trier.centerhotels.services.EmployeeService;
import br.com.trier.centerhotels.services.impl.CustomerServiceImpl;
import br.com.trier.centerhotels.services.impl.EmployeeServiceImpl;

@TestConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class BaseTests {

	@Bean
	public CustomerService customerService() {
		return new CustomerServiceImpl();
	}
	
	@Bean
	public EmployeeService employeeService() {
		return new EmployeeServiceImpl();
	}
	
	

}
