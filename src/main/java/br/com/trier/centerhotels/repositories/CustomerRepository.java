package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.users.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
