package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.dto.ReservationDTO;
import br.com.trier.centerhotels.services.impl.ReservationServiceImpl;

@RestController
@RequestMapping("/reservations")
public class ReservationResource extends BaseResource<Reservation, Integer, ReservationDTO, ReservationServiceImpl> {

	@Autowired
	public ReservationResource(ReservationServiceImpl service) {
		super(service);
	}

	@Override
	protected void setEntityId(Reservation entity, Integer id) {
		entity.setId(id);
	}

	@Override
	protected Reservation convertDtoToEntity(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ReservationDTO convertEntityToDto(Reservation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
