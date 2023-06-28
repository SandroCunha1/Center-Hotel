package br.com.trier.centerhotels.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.dto.RoomTypeDTO;
import br.com.trier.centerhotels.services.impl.RoomTypeServiceImpl;

@RestController
@RequestMapping("/room-types")
public class RoomTypeResource extends BaseResource<RoomType, Integer, RoomTypeDTO, RoomTypeServiceImpl> {

    @Autowired
    public RoomTypeResource(RoomTypeServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(RoomType entity, Integer id) {
        entity.setId(id);
    }

	@Override
	protected RoomType convertDtoToEntity(RoomTypeDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RoomTypeDTO convertEntityToDto(RoomType entity) {
		// TODO Auto-generated method stub
		return null;
	}




}