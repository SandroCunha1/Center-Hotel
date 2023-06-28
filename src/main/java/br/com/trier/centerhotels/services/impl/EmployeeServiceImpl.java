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
	public Employee insert(Employee employee) {
		validateUser(employee);
		return super.insert(employee);
	}

	@Override
	public Employee update(Employee employee) {
		validateUser(employee);
		return super.update(employee);
	}


	@Override
	public List<Employee> findByUserStartsWithIgnoreCase(String user) {
		return findByTemplate("findByUserStartsWithIgnoreCase",
				"Nenhum funcionário/usuário começa com:",
				user);
	}

	@Override
	public List<Employee> findByNameStartsWithIgnoreCase(String name) {
		return findByTemplate("findByNameStartsWithIgnoreCase",
				"Nenhum funcionário/nome começa com:",
				name);
	}

	@Override
	public List<Employee> findByCpfStartsWith(String cpf) {
		return findByTemplate("findByCpfStartsWith",
				"Nenhum funcionário/cpf igual á :",
				cpf);
	}

	@Override
	public List<Employee> findByCepStartsWith(String cep) {
		return findByTemplate("findByCepStartsWith",
				"Nenhum funcionário/cep igual á :",
				cep);
	}

	@Override
	public List<Employee> findByEmailStartsWithIgnoreCase(String email) {
		return findByTemplate("findByEmailStartsWithIgnoreCase",
				"Nenhum funcionário/email igual á :",
				email);
	}

	@Override
	public List<Employee> findByCepStartsWithAndNameStartsWithIgnoreCase(String cep, String name) {
		return findByTemplateTwo("findByCepStartsWithAndNameStartsWithIgnoreCase",
				"Nenhum funcionário/cep e funcionário/name igual á :",
				cep, name);
	}

	@Override
	public List<Employee> findByHotel(Hotel hotel) {
		return findByTemplate("findByHotel",
				"Nenhum funcionário no hotel igual á :",
				hotel);
	}
}
