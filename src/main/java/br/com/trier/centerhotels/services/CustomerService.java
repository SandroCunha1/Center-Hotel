package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.users.Customer;

public interface CustomerService extends BaseCrudService<Customer> {
	List<Customer> findByUserStartsWithIgnoreCase(String user);
	List<Customer> findByNameStartsWithIgnoreCase(String name);
	List<Customer> findByCpf(String cpf);
	List<Customer> findByCep(Integer cep);
	List<Customer> findByEmailStartsWithIgnoreCase(String email);
	List<Customer> findByCepAndNameStartsWithIgnoreCase(Integer cep, String name);
	
}
