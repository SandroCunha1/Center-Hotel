package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.services.impl.HotelServiceImpl;

@RestController
@RequestMapping("/hotels")
public class HotelResource extends BaseResource<Hotel, Integer, HotelServiceImpl> {

    @Autowired
    public HotelResource(HotelServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(Hotel entity, Integer id) {
        entity.setId(id);
    }
}