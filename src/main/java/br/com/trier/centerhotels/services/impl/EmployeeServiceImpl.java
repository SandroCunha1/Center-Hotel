package br.com.trier.centerhotels.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;
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


	@Override
	public List<Employee> findByUserStartsWithIgnoreCase(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByNameStartsWithIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByCpfStartsWith(Integer cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByCepStartsWith(Integer cep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByEmailStartsWithIgnoreCase(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByCpfStartsWithAndNameStartsWithIgnoreCase(Integer cpf, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByHotel(Hotel hotel) {
		// TODO Auto-generated method stub
		return null;
	}
}
