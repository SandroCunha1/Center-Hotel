package br.com.trier.centerhotels.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByUserStartsWithIgnoreCase(String user);
	List<Employee> findByNameStartsWithIgnoreCase(String name);
	List<Employee> findByCpfStartsWith(String cpf);
	List<Employee> findByCepStartsWith(String cep);
	List<Employee> findByEmailStartsWithIgnoreCase(String email);
	List<Employee> findByCepStartsWithAndNameStartsWithIgnoreCase(String cep, String name);
	List<Employee> findByHotel(Hotel hotel);
	
}
