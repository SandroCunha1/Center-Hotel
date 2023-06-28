package br.com.trier.centerhotels.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.repositories.CustomerRepository;
import br.com.trier.centerhotels.services.CustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Integer> implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	protected JpaRepository<Customer, Integer> getRepository() {
		return repository;
	}

	@Override
	public Customer insert(Customer customer) {
		validateUser(customer);
		return super.insert(customer);
	}

	@Override
	public Customer update(Customer customer) {
		validateUser(customer);
		return super.update(customer);
	}

	@Override
	public List<Customer> findByUserStartsWithIgnoreCase(String user) {
		return findByTemplate("findByUserStartsWithIgnoreCase",
				"Nenhum cliente/usuário começa com:",
				user);
	}

	@Override
	public List<Customer> findByNameStartsWithIgnoreCase(String name) {
		return findByTemplate("findByNameStartsWithIgnoreCase",
				"Nenhum cliente/nome encontrado com:",
				name);
	}

	@Override
	public List<Customer> findByCpf(String cpf) {
		return findByTemplate("findByCpf",
				"Nenhum cliente/cpf encontrado com:",
				cpf);
	}

	@Override
	public List<Customer> findByCep(String cep) {
		return findByTemplate("findByCep",
				"Nenhum cliente/cep encontrado com:",
				cep);
	}

	@Override
	public List<Customer> findByEmailStartsWithIgnoreCase(String email) {
		return findByTemplate("findByEmailStartsWithIgnoreCase",
				"Nenhum cliente/email encontrado com:",
				email);
	}

	@Override
	public List<Customer> findByCepAndNameStartsWithIgnoreCase(String cep, String name) {
		return findByTemplateTwo("findByCepAndNameStartsWithIgnoreCase",
				"Nenhum cliente/cep e cliente/name encontrado com:",
				cep,
				name);
	}

	@Override
	protected Integer getEntityId(Customer entity) {
		return entity.getId();
	}

}
