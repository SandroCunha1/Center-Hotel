package br.com.trier.centerhotels.services.impl;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.repositories.ReservationRepository;
import br.com.trier.centerhotels.services.ReservationService;

@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation, Integer> implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    protected JpaRepository<Reservation, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(Reservation entity) {
        return entity.getId();
    }


	@Override
	public List<Reservation> findByCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByRoom(HotelRoom room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByDateInit(ZonedDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByDateFin(ZonedDateTime date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByDateInitAndDateFin(ZonedDateTime dateInit, ZonedDateTime dateFin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByDateInitBetween(ZonedDateTime date, ZonedDateTime date2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> findByDateFinBetween(ZonedDateTime date, ZonedDateTime date2) {
		// TODO Auto-generated method stub
		return null;
	}
}
