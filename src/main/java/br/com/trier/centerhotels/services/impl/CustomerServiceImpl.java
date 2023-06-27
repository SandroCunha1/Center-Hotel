package br.com.trier.centerhotels.services.impl;

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
	protected Integer getEntityId(Customer entity) {
		return entity.getId();
	}

}
