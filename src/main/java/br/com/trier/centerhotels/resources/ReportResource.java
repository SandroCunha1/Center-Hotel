package br.com.trier.centerhotels.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.dto.HotelReservationsDTO;
import br.com.trier.centerhotels.models.dto.HotelRoomDTO;
import br.com.trier.centerhotels.models.dto.HotelRoomsDTO;
import br.com.trier.centerhotels.models.dto.HotelTypesDTO;
import br.com.trier.centerhotels.models.dto.ReservationDTO;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.CustomerService;
import br.com.trier.centerhotels.services.EmployeeService;
import br.com.trier.centerhotels.services.HotelRoomService;
import br.com.trier.centerhotels.services.HotelService;
import br.com.trier.centerhotels.services.ReservationService;
import br.com.trier.centerhotels.services.RoomTypeService;


@RestController
@RequestMapping("/report")
public class ReportResource {

	@Autowired
	HotelRoomService roomService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	CustomerService customerService;
	
    @Autowired
	RoomTypeService typeService;
    
    @GetMapping("/hotel-reservations/{hotelId}")
	public ResponseEntity<HotelReservationsDTO> findHotelReservations(@PathVariable Integer hotelId) {
    	Hotel hotel = hotelService.findById(hotelId);
    	List<HotelRoom> rooms = roomService.findByHotel(hotel);
    	 List<ReservationDTO> reservations = rooms.stream()
    		        .flatMap(room -> reservationService.findByRoom(room).stream())
    		        .map(reservation -> reservation.toDto())
    		        .collect(Collectors.toList());
    	 return ResponseEntity.ok(new HotelReservationsDTO(hotelId, hotel.getName(), reservations.size(), reservations)) ;
		
	}
    
    @GetMapping("/hotel-roomtypes/{hotelId}")
	public ResponseEntity<HotelTypesDTO> findHotelRoomTypes(@PathVariable Integer hotelId) {
    	Hotel hotel = hotelService.findById(hotelId);
    	List<HotelRoom> rooms = roomService.findByHotel(hotel);
    	 List<RoomType> types = rooms.stream()
    	            .map(HotelRoom::getType)
    	            .distinct()
    	            .collect(Collectors.toList());
    	 
    	return ResponseEntity.ok(new HotelTypesDTO(hotelId, hotel.getName(), types.size(), types)) ;
		
	}
    
    @GetMapping("/hotel-rooms/{hotelId}")
	public ResponseEntity<HotelRoomsDTO> findHotelRooms(@PathVariable Integer hotelId) {
    	Hotel hotel = hotelService.findById(hotelId);
    	List<HotelRoomDTO> rooms = roomService.findByHotel(hotel).stream().map(HotelRoom::toDto).toList();	 
    	return ResponseEntity.ok(new HotelRoomsDTO(hotelId, hotel.getName(), rooms.size(), rooms)) ;
		
	}
	
}
