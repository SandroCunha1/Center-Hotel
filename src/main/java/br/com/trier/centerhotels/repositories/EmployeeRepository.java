package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.users.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
