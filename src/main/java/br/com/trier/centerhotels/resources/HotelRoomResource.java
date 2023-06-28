package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
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




}
