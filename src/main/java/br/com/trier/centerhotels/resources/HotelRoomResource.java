package br.com.trier.centerhotels.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.dto.HotelRoomDTO;
import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.HotelService;
import br.com.trier.centerhotels.services.RoomTypeService;
import br.com.trier.centerhotels.services.impl.HotelRoomServiceImpl;

@RestController
@RequestMapping("/hotel-rooms")
public class HotelRoomResource extends BaseResource<HotelRoom, Integer, HotelRoomDTO, HotelRoomServiceImpl> {

    @Autowired
    public HotelRoomResource(HotelRoomServiceImpl service) {
        super(service);
    }
    
    @Autowired
	HotelService hotelService;
    
    @Autowired
	RoomTypeService typeService;

    @Override
    protected void setEntityId(HotelRoom entity, Integer id) {
        entity.setId(id);
    }

	@Override
	protected HotelRoom convertDtoToEntity(HotelRoomDTO dto) {
		Hotel hotel = hotelService.findById(dto.getHotelId());
		RoomType type = typeService.findById(dto.getTypeId());
		return new HotelRoom(dto, hotel, type);
	}

	@Override
	protected HotelRoomDTO convertEntityToDto(HotelRoom entity) {
		return entity.toDto();
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/hotel/{hotelId}")
	public ResponseEntity<List<HotelRoomDTO>> findByHotel(@PathVariable Integer hotelId) {
		return ResponseEntity.ok(convertListToDto(service.findByHotel(hotelService.findById(hotelId))) );
	}	
	
	@Secured({"ROLE_USER"})
	@GetMapping("/type/{typeId}")
	public ResponseEntity<List<HotelRoomDTO>> findByType(@PathVariable Integer typeId) {
		return ResponseEntity.ok(convertListToDto(service.findByType(typeService.findById(typeId))) );
	}	
	
	@Secured({"ROLE_USER"})
	@GetMapping("/hotel/{hotelId}/num/{num}")
	public ResponseEntity<List<HotelRoomDTO>> findByHotelAndNum(@PathVariable Integer hotelId,@PathVariable Integer num) {
		return ResponseEntity.ok(convertListToDto(service.findByHotelAndNum(hotelService.findById(hotelId), num)) );
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/hotel/{hotelId}/type/{typeId}")
	public ResponseEntity<List<HotelRoomDTO>> findByHotelAndType(@PathVariable Integer hotelId, @PathVariable Integer typeId) {
		return ResponseEntity.ok(convertListToDto(service.findByHotelAndType(hotelService.findById(hotelId),typeService.findById(typeId))) );
	}





}
