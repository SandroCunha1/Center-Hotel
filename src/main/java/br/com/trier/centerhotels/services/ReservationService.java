package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;

public interface ReservationService extends BaseCrudService<Reservation> {
	List<Reservation> findByCustomer(Customer customer);
	List<Reservation> findByEmployee(Employee employee);
	List<Reservation> findByRoom(HotelRoom room);
	List<Reservation> findByDateInit(String date);
	List<Reservation> findByDateFin(String date);
	List<Reservation> findByDateInitAndDateFin(String dateInit, String dateFin);
	List<Reservation> findByDateInitBetween(String date, String date2);
	List<Reservation> findByDateFinBetween(String date, String date2);
}
