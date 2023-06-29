package br.com.trier.centerhotels.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.dto.CustomerDTO;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.services.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomerResource extends BaseResource<Customer, Integer, CustomerDTO, CustomerServiceImpl> {

	@Autowired
	public CustomerResource(CustomerServiceImpl service) {
		super(service);
	}

	@Override
	protected void setEntityId(Customer entity, Integer id) {
		entity.setId(id);
	}
	
	@Override
	protected Customer convertDtoToEntity(CustomerDTO dto) {
			return new Customer(dto);
	}

	@Override
	protected CustomerDTO convertEntityToDto(Customer entity) {
		return entity.toDTO();
	}

	@GetMapping("/user/{user}")
	public ResponseEntity<List<CustomerDTO>> findByUserStartsWithIgnoreCase(@PathVariable String user) {
		return ResponseEntity.ok(convertListToDto(service.findByUserStartsWithIgnoreCase(user)));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<CustomerDTO>> findByNameStartsWithIgnoreCase(@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByNameStartsWithIgnoreCase(name)));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<CustomerDTO>> findByEmailStartsWithIgnoreCase(@PathVariable String email) {
		return ResponseEntity.ok(convertListToDto(service.findByEmailStartsWithIgnoreCase(email)));
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<List<CustomerDTO>> findByCepStartsWith(@PathVariable String cep) {
		return ResponseEntity.ok(convertListToDto(service.findByCepStartsWith(cep)));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<CustomerDTO>> findByCpfStartsWith(@PathVariable String cpf) {
		return ResponseEntity.ok(convertListToDto(service.findByCpfStartsWith(cpf)));
	}

	@GetMapping("/cep-name/{cep}/{name}")
	public ResponseEntity<List<CustomerDTO>> findByCepAndNameStartsWithIgnoreCase(@PathVariable String cep,
			@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByCepStartsWithAndNameStartsWithIgnoreCase(cep, name)));

	}



}
