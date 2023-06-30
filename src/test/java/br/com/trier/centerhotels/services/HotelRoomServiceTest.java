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
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/user.sql")  
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/hotel.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/room_type.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/hotel_room.sql")
public class HotelRoomServiceTest extends BaseTests {

	@Autowired
	HotelRoomService roomService;
	
	@Test
	@DisplayName("Update usuario")
	void update() {	
		Hotel h = new Hotel(50, null, null, null, null, null, null, null, null, null);
		RoomType t = new RoomType(10, null, null, null);
		HotelRoom hr = new HotelRoom(10, 100, h, t);
		roomService.update(hr);
		assertEquals(3, roomService.listAll().size());
	}
	
	@Test
	@DisplayName("Procura por hotel")
	void searchByHotel() {	
		Hotel hotel = new Hotel(50, null, null, null, null, null, null, null, null, null);
		assertEquals(1, roomService.findByHotel(hotel).size());
		hotel.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		roomService.findByHotel(hotel).size());
		assertEquals("Nenhum quarto registrado em : Hotel(description=null, cnpj=null)", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por tipo")
	void searchByType() {	
		RoomType type = new RoomType(10, null, null, null);
		assertEquals(1, roomService.findByType(type).size());
		type.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		roomService.findByType(type).size());
		assertEquals("Nenhum quarto registrado com tipo : RoomType(id=99, description=null, qt=null, daily=null)", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por hotel e tipo")
	void searchByHotelAndType() {	
		Hotel hotel = new Hotel(50, null, null, null, null, null, null, null, null, null);
		RoomType type = new RoomType(10, null, null, null);
		assertEquals(1, roomService.findByHotelAndType(hotel, type).size());
		hotel.setId(99);
		var ex = assertThrows(ObjectNotFound.class, () ->
		roomService.findByHotelAndType(hotel, type).size());
		assertEquals("Nenhum nenhum quarto registrado com hotel e tipo igual á Hotel(description=null, cnpj=null) e RoomType(id=10, description=null, qt=null, daily=null)", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por hotel e num")
	void searchByHotelAndNum() {	
		Hotel hotel = new Hotel(50, null, null, null, null, null, null, null, null, null);
		assertEquals(1, roomService.findByHotelAndNum(hotel, 101).size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		roomService.findByHotelAndNum(hotel, 102).size());
		assertEquals("Nenhum nenhum quarto registrado com hotel e número igual á Hotel(description=null, cnpj=null) e 102", ex.getMessage());
	}
	
	

}
