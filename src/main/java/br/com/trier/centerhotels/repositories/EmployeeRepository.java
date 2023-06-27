package br.com.trier.centerhotels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByUserStartsWithIgnoreCase(String user);
	List<Employee> findByNameStartsWithIgnoreCase(String name);
	List<Employee> findByCpfStartsWith(Integer cpf);
	List<Employee> findByCepStartsWith(Integer cep);
	List<Employee> findByEmailStartsWithIgnoreCase(String email);
	List<Employee> findByCpfStartsWithAndNameStartsWithIgnoreCase(Integer cpf, String name);
	List<Employee> findByHotel(Hotel hotel);
	
}
