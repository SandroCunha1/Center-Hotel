package br.com.trier.centerhotels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.users.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findByUserStartsWithIgnoreCase(String user);
	List<Customer> findByNameStartsWithIgnoreCase(String name);
	List<Customer> findByCpf(String cpf);
	List<Customer> findByCep(Integer cep);
	List<Customer> findByEmailStartsWithIgnoreCase(String email);
	List<Customer> findByCepAndNameStartsWithIgnoreCase(Integer cpf, String name);
}
