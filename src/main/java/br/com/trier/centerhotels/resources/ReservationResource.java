package br.com.trier.centerhotels.resources;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.dto.ReservationDTO;
import br.com.trier.centerhotels.services.CustomerService;
import br.com.trier.centerhotels.services.EmployeeService;
import br.com.trier.centerhotels.services.HotelRoomService;
import br.com.trier.centerhotels.services.impl.ReservationServiceImpl;

@RestController
@RequestMapping("/reservations")
public class ReservationResource extends BaseResource<Reservation, Integer, ReservationDTO, ReservationServiceImpl> {

	@Autowired
	public ReservationResource(ReservationServiceImpl service) {
		super(service);
	}
	
	@Autowired
	HotelRoomService roomService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CustomerService customerService;

	@Override
	protected void setEntityId(Reservation entity, Integer id) {
		entity.setId(id);
	}

	@Override
	protected Reservation convertDtoToEntity(ReservationDTO dto) {
		try {
		return new Reservation(dto, roomService.findById(dto.getRoomId()), employeeService.findById(dto.getEmployeeId()), customerService.findById(dto.getCustomerId()));
		} catch (Exception e) {
			try {
			return new Reservation(dto, customerService.findById(dto.getCustomerId()), roomService.findById(dto.getRoomId()));
			} catch (Exception e2) {
			return new Reservation(dto, employeeService.findById(dto.getEmployeeId()), roomService.findById(dto.getRoomId()));
			}
		}
	}

	@Override
	protected ReservationDTO convertEntityToDto(Reservation entity) {
		return entity.toDto();
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/room/{roomId}")
	public ResponseEntity<List<ReservationDTO>> findByRoom(@PathVariable Integer roomId) {
		return ResponseEntity.ok(convertListToDto(service.findByRoom(roomService.findById(roomId))) );
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/employee/{empId}")
	public ResponseEntity<List<ReservationDTO>> findByEmployee(@PathVariable Integer empId) {
		return ResponseEntity.ok(convertListToDto(service.findByEmployee(employeeService.findById(empId))));
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/customer/{custId}")
	public ResponseEntity<List<ReservationDTO>> findByCustomer(@PathVariable Integer custId) {
		return ResponseEntity.ok(convertListToDto(service.findByCustomer(customerService.findById(custId))) );
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/init-between/{init}/{fin}")
	public ResponseEntity<List<ReservationDTO>> findByDateInitBetween(@PathVariable String init, @PathVariable String fin) {
		return ResponseEntity.ok(convertListToDto(service.findByDateInitBetween(init, fin)) );
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/fin-between/{init}/{fin}")
	public ResponseEntity<List<ReservationDTO>> findByDateFinBetween(@PathVariable String init, @PathVariable String fin) {
		return ResponseEntity.ok(convertListToDto(service.findByDateFinBetween(init, fin)) );
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/init-fin/{init}/{fin}")
	public ResponseEntity<List<ReservationDTO>> findByDateInitAndDateFin(@PathVariable String init, @PathVariable String fin) {
		return ResponseEntity.ok(convertListToDto(service.findByDateInitAndDateFin(init, fin)) );
	}
	
	

}
