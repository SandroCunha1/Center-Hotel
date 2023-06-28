package br.com.trier.centerhotels.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.dto.EmployeeDTO;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.HotelService;
import br.com.trier.centerhotels.services.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeResource extends BaseResource<Employee, Integer, EmployeeDTO, EmployeeServiceImpl> {

	@Autowired
	public EmployeeResource(EmployeeServiceImpl service) {
		super(service);
	}

	@Autowired
	HotelService hotelService;

	@Override
	protected void setEntityId(Employee entity, Integer id) {
		entity.setId(id);
	}
	
	@Override
	protected Employee convertDtoToEntity(EmployeeDTO dto) {
			Hotel hotel = hotelService.findById(dto.getHotelId());
			return new Employee(dto, hotel);

	}

	@Override
	protected EmployeeDTO convertEntityToDto(Employee entity) {
		return entity.toDTO();
	}

	@GetMapping("/user/{user}")
	public ResponseEntity<List<EmployeeDTO>> findByUserStartsWithIgnoreCase(@PathVariable String user) {
		return ResponseEntity.ok(convertListToDto(service.findByUserStartsWithIgnoreCase(user)));
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<EmployeeDTO>> findByNameStartsWithIgnoreCase(@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByNameStartsWithIgnoreCase(name)) );
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<EmployeeDTO>> findByEmailStartsWithIgnoreCase(@PathVariable String email) {
		return ResponseEntity.ok(convertListToDto(service.findByEmailStartsWithIgnoreCase(email)));
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<List<EmployeeDTO>> findByCepStartsWith(@PathVariable String cep) {
		return ResponseEntity.ok(convertListToDto(service.findByCepStartsWith(cep)));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<List<EmployeeDTO>> findByCpfStartsWith(@PathVariable String cpf) {
		return ResponseEntity.ok(convertListToDto(service.findByCpfStartsWith(cpf)));
	}

	@GetMapping("/cep-name/{cep}/{name}")
	public ResponseEntity<List<EmployeeDTO>> findByCepAndNameStartsWithIgnoreCase(@PathVariable String cep,
			@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByCepStartsWithAndNameStartsWithIgnoreCase(cep, name)));
	}

	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<EmployeeDTO>> findByHotel(@PathVariable Integer hotelId) {
		return ResponseEntity.ok(convertListToDto(service.findByHotel(hotelService.findById(hotelId))) );
	}	

}