package br.com.trier.centerhotels.repositories;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	List<Reservation> findByCustomer(Customer customer);
	List<Reservation> findByEmployee(Employee employee);
	List<Reservation> findByRoom(HotelRoom room);
	List<Reservation> findByDateInitAndDateFin(ZonedDateTime dateInit, ZonedDateTime dateFin);
	List<Reservation> findByDateInitBetween(ZonedDateTime date, ZonedDateTime date2);
	List<Reservation> findByDateFinBetween(ZonedDateTime date, ZonedDateTime date2);
	List<Reservation> findByDateInitGreaterThanEqualAndDateFinLessThanEqual(ZonedDateTime date, ZonedDateTime date2);
	List<Reservation> findByDateInitLessThanEqualAndDateFinGreaterThanEqual (ZonedDateTime date, ZonedDateTime date2);
	
}
