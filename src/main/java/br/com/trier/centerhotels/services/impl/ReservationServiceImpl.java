package br.com.trier.centerhotels.services.impl;

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
import br.com.trier.centerhotels.utils.DateUtils;

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
		return findByTemplate("findByCustomer",
				"Nenhuma rezerva registrado no cliente :",
				customer);
	}

	@Override
	public List<Reservation> findByEmployee(Employee employee) {
		return findByTemplate("findByEmployee",
				"Nenhuma rezerva registrada pelo funcionario :",
				employee);
	}

	@Override
	public List<Reservation> findByRoom(HotelRoom room) {
		return findByTemplate("findByRoom",
				"Nenhuma rezerva registrada no quarto :",
				room);
	}

	@Override
	public List<Reservation> findByDateInit(String date) {
		DateUtils.dateBrToZoneDate(date);
		return findByTemplate("findByDateInit",
				"Nenhuma rezerva com data inicial :",
				date);
	}

	@Override
	public List<Reservation> findByDateFin(String date) {
		DateUtils.dateBrToZoneDate(date);
		return findByTemplate("findByDateFin",
				"Nenhuma rezerva com data final :",
				date);
	}

	@Override
	public List<Reservation> findByDateInitAndDateFin(String dateInit, String dateFin) {
		DateUtils.dateBrToZoneDate(dateInit);
		DateUtils.dateBrToZoneDate(dateFin);
		return findByTemplateTwo("findByDateInitAndDateFin",
				"Nenhuma rezerva com data inicial e data final em :",
				dateInit, dateFin);
	}

	@Override
	public List<Reservation> findByDateInitBetween(String date, String date2) {
		DateUtils.dateBrToZoneDate(date);
		DateUtils.dateBrToZoneDate(date2);
		return findByTemplateTwo("findByDateInitBetween",
				"Nenhuma rezerva com data inicial entre :",
				date, date2);
	}

	@Override
	public List<Reservation> findByDateFinBetween(String date, String date2) {
		DateUtils.dateBrToZoneDate(date);
		DateUtils.dateBrToZoneDate(date2);
		return findByTemplateTwo("findByDateFinBetween",
				"Nenhuma rezerva com data final entre :",
				date, date2);
	}
}
