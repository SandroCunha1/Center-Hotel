package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.services.impl.RoomTypeServiceImpl;

@RestController
@RequestMapping("/room-types")
public class RoomTypeResource extends BaseResource<RoomType, Integer, RoomTypeServiceImpl> {

    @Autowired
    public RoomTypeResource(RoomTypeServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(RoomType entity, Integer id) {
        entity.setId(id);
    }
}