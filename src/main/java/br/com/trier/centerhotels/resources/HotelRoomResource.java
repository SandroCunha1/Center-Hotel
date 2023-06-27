package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.services.impl.HotelRoomServiceImpl;

@RestController
@RequestMapping("/hotel-rooms")
public class HotelRoomResource extends BaseResource<HotelRoom, Integer, HotelRoomServiceImpl> {

    @Autowired
    public HotelRoomResource(HotelRoomServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(HotelRoom entity, Integer id) {
        entity.setId(id);
    }
}
