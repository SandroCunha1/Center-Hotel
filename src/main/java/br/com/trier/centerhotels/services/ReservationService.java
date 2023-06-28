package br.com.trier.centerhotels.services;

import java.time.ZonedDateTime;
import java.util.List;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;

public interface ReservationService extends BaseCrudService<Reservation> {
	List<Reservation> findByCustomer(Customer customer);
	List<Reservation> findByEmployee(Employee employee);
	List<Reservation> findByRoom(HotelRoom room);
	List<Reservation> findByDateInit(ZonedDateTime date);
	List<Reservation> findByDateFin(ZonedDateTime date);
	List<Reservation> findByDateInitAndDateFin(ZonedDateTime dateInit, ZonedDateTime dateFin);
	List<Reservation> findByDateInitBetween(ZonedDateTime date, ZonedDateTime date2);
	List<Reservation> findByDateFinBetween(ZonedDateTime date, ZonedDateTime date2);
}
