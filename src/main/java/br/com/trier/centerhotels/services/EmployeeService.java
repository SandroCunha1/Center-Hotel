package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;

public interface EmployeeService extends BaseCrudService<Employee> {
	List<Employee> findByUserStartsWithIgnoreCase(String user);
	List<Employee> findByNameStartsWithIgnoreCase(String name);
	List<Employee> findByCpfStartsWith(String cpf);
	List<Employee> findByCepStartsWith(String cep);
	List<Employee> findByEmailStartsWithIgnoreCase(String email);
	List<Employee> findByCepStartsWithAndNameStartsWithIgnoreCase(String cep, String name);
	List<Employee> findByHotel(Hotel hotel);
}
