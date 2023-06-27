package br.com.trier.centerhotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.repositories.EmployeeRepository;
import br.com.trier.centerhotels.services.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Integer> implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    protected JpaRepository<Employee, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(Employee entity) {
        return entity.getId();
    }
}
