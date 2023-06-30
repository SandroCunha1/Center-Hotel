package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.exceptions.IntegrityViolation;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import br.com.trier.centerhotels.utils.DateUtils;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/user.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/hotel.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/employee.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/customer.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/room_type.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/hotel_room.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/reservation.sql")
public class ReservationServiceTest extends BaseTests {

	@Autowired
	ReservationService service;

	@Autowired
	CustomerService customerService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	HotelRoomService roomService;

	@Test
	@DisplayName("Insert nova reserva")
	void insert() {
		Reservation r = new Reservation(1, DateUtils.dateBrToZoneDate("01-10-2003 00:00"),
				DateUtils.dateBrToZoneDate("02-10-2003 01:00"), null, roomService.findById(10), null);
		service.insert(r);
		assertEquals(4, service.listAll().size());
		assertEquals(1, r.getId());
	}
	
	@Test
	@DisplayName("Insert nova reserva datas conflitantes")
	void insertDateConflict() {
		Reservation r = new Reservation(1, DateUtils.dateBrToZoneDate("30-06-2023 00:00"),
				DateUtils.dateBrToZoneDate("02-07-2023 01:00"), null, roomService.findById(10), null);
		
		var ex = assertThrows(IntegrityViolation.class, () ->
		service.insert(r));
		assertEquals("Conflito de datas entre reservas", ex.getMessage());
		assertEquals(3, service.listAll().size());
	}
	
	@Test
	@DisplayName("Insert nova reserva data init depois de data fin")
	void insertDateInitBigger() {
		Reservation r = new Reservation(1, DateUtils.dateBrToZoneDate("02-10-2023 00:00"),
				DateUtils.dateBrToZoneDate("01-10-2023 01:00"), null, roomService.findById(10), null);
		
		var ex = assertThrows(IntegrityViolation.class, () ->
		service.insert(r));
		assertEquals("Data inicial deve ser antes que data final", ex.getMessage());
		assertEquals(3, service.listAll().size());
	}
	
	@Test
	@DisplayName("Insert nova reserva datas conflitantes quartos diferentes")
	void insertDateConflictDiferentRoom() {
		Reservation r = new Reservation(1, DateUtils.dateBrToZoneDate("30-06-2023 00:00"),
				DateUtils.dateBrToZoneDate("02-07-2023 01:00"), null, roomService.findById(30), null);
		service.insert(r);
		assertEquals(4, service.listAll().size());
	}
	
	@Test
	@DisplayName("Update reserva")
	void update() {
		Reservation r = new Reservation(10, DateUtils.dateBrToZoneDate("01-10-2003 00:00"),
				DateUtils.dateBrToZoneDate("02-10-2003 01:00"), null, roomService.findById(10), null);
		service.update(r);
		assertEquals(3, service.listAll().size());
	}
	
	@Test
	@DisplayName("Update conflito de data com a mesma reserva")
	void updateConflictSame() {
		Reservation r = new Reservation(10, DateUtils.dateBrToZoneDate("30-06-2023 00:00"),
				DateUtils.dateBrToZoneDate("01-07-2023 01:00"), null, roomService.findById(10), null);
		service.update(r);
		assertEquals(3, service.listAll().size());
	}
	
	@Test
	@DisplayName("Procura por cliente")
	void searchByCustomer() {	
		Customer c = new Customer(20, null, null, null, null, null, null, null, null);
		assertEquals(1, service.findByCustomer(c).size());
		c.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByCustomer(c).size());
		assertEquals("Nenhuma reserva registrado no cliente : Customer(cpf=null)", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por funcionário")
	void searchByEmployee() {	
		Hotel hotel = new Hotel(50, null, null, null, null, null, null, null, null, null);
		Employee e = new Employee(80, null, null, null, null, null, null, null, hotel, null);
		assertEquals(1, service.findByEmployee(e).size());
		e.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByEmployee(e).size());
		assertEquals("Nenhuma reserva registrada pelo funcionário : Employee(cpf=null, hotel=Hotel(description=null, cnpj=null))", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por quarto")
	void searchByRoom() {	
		HotelRoom r = new HotelRoom(10, null, null, null);
		assertEquals(1, service.findByRoom(r).size());
		r.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByRoom(r).size());
		assertEquals("Nenhuma reserva registrada no quarto : HotelRoom(id=99, num=null, hotel=null, type=null)", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por data inicial entre")
	void searchByInit() {	
		assertEquals(1, service.findByDateInitBetween("01-06-2023 10:00", "02-07-2023 10:00").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByDateInitBetween("11-07-2003 10:00","12-07-2003 10:00").size());
		assertEquals("Nenhuma reserva com data inicial entre : 2003-07-11T00:00-03:00[America/Sao_Paulo] e 2003-07-12T00:00-03:00[America/Sao_Paulo]", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por data final entre")
	void searchByFinal() {	
		assertEquals(1, service.findByDateFinBetween("01-06-2023 10:00", "02-07-2023 10:00").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByDateFinBetween("11-07-2003 10:00","12-07-2003 10:00").size());
		assertEquals("Nenhuma reserva com data final entre : 2003-07-11T00:00-03:00[America/Sao_Paulo] e 2003-07-12T00:00-03:00[America/Sao_Paulo]", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por data inicial e final igual á")
	void searchByInitAndFin() {	
		Reservation r = new Reservation(1, DateUtils.dateBrToZoneDate("20-06-2023 00:00"),
				DateUtils.dateBrToZoneDate("21-06-2023 01:00"), null, roomService.findById(10), null);
		service.insert(r);
		assertEquals(1, service.findByDateInitAndDateFin("20-06-2023 00:00", "21-06-2023 01:00").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		service.findByDateInitAndDateFin("11-07-2003 10:00","12-07-2003 10:00").size());
		assertEquals("Nenhuma reserva com data inicial e data final em : 2003-07-11T00:00-03:00[America/Sao_Paulo] e 2003-07-12T00:00-03:00[America/Sao_Paulo]", ex.getMessage());
	}

}
