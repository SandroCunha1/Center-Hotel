package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.users.Customer;

public interface CustomerService extends BaseCrudService<Customer> {
	List<Customer> findByUserStartsWithIgnoreCase(String user);
	List<Customer> findByNameStartsWithIgnoreCase(String name);
	List<Customer> findByCpfStartsWith(String cpf);
	List<Customer> findByCepStartsWith(String cep);
	List<Customer> findByEmailStartsWithIgnoreCase(String email);
	List<Customer> findByCepStartsWithAndNameStartsWithIgnoreCase(String cep, String name);
	
}
