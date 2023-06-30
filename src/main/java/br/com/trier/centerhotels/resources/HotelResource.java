package br.com.trier.centerhotels.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.dto.HotelDTO;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelResource extends BaseResource<Hotel, Integer, HotelDTO, HotelServiceImpl> {

	@Autowired
	public HotelResource(HotelServiceImpl service) {
		super(service);
	}

	@Override
	protected void setEntityId(Hotel entity, Integer id) {
		entity.setId(id);
	}

	@Override
	protected Hotel convertDtoToEntity(HotelDTO dto) {
			return new Hotel(dto);
	
	}

	@Override
	protected HotelDTO convertEntityToDto(Hotel entity) {
		return entity.toDTO();
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/user/{user}")
	public ResponseEntity<List<HotelDTO>> findByUserStartsWithIgnoreCase(@PathVariable String user) {
		return ResponseEntity.ok(convertListToDto(service.findByUserStartsWithIgnoreCase(user)));
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/name/{name}")
	public ResponseEntity<List<HotelDTO>> findByNameStartsWithIgnoreCase(@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByNameStartsWithIgnoreCase(name)) );
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/email/{email}")
	public ResponseEntity<List<HotelDTO>> findByEmailStartsWithIgnoreCase(@PathVariable String email) {
		return ResponseEntity.ok(convertListToDto(service.findByEmailStartsWithIgnoreCase(email)));
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/cep/{cep}")
	public ResponseEntity<List<HotelDTO>> findByCepStartsWith(@PathVariable String cep) {
		return ResponseEntity.ok(convertListToDto(service.findByCepStartsWith(cep)));
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/cnpj/{cpf}")
	public ResponseEntity<List<HotelDTO>> findByCnpjStartsWith(@PathVariable String cnpj) {
		return ResponseEntity.ok(convertListToDto(service.findByCnpjStartsWith(cnpj)));
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/cnpj-name/{cnpj}/{name}")
	public ResponseEntity<List<HotelDTO>> findByCepAndNameStartsWithIgnoreCase(@PathVariable String cnpj,
			@PathVariable String name) {
		return ResponseEntity.ok(convertListToDto(service.findByCnpjStartsWithAndNameStartsWithIgnoreCase(cnpj, name)));
	}

	@Secured({"ROLE_USER"})
	@GetMapping("/desc/{desc}")
	public ResponseEntity<List<HotelDTO>> findByDesc(@PathVariable String desc) {
		return ResponseEntity.ok(convertListToDto(service.findByDescriptionContainingIgnoreCase(desc)) );
	}	
	
}