package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.dto.ReservationAbstractDTO;
import br.com.trier.centerhotels.services.CustomerService;
import br.com.trier.centerhotels.services.EmployeeService;
import br.com.trier.centerhotels.services.HotelRoomService;
import br.com.trier.centerhotels.services.impl.ReservationServiceImpl;

@RestController
@RequestMapping("/reservations")
public class ReservationResource extends BaseResource<Reservation, Integer, ReservationAbstractDTO, ReservationServiceImpl> {

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
	protected Reservation convertDtoToEntity(ReservationAbstractDTO dto) {
		return null;
		
	}

	@Override
	protected ReservationAbstractDTO convertEntityToDto(Reservation entity) {
		return entity.toDto();
	}

}
