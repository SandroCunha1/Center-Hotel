package br.com.trier.centerhotels.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.services.impl.RoomTypeServiceImpl;

@RestController
@RequestMapping("/room-types")
public class RoomTypeResource extends BaseResource<RoomType, Integer, RoomType, RoomTypeServiceImpl> {

    @Autowired
    public RoomTypeResource(RoomTypeServiceImpl service) {
        super(service);
    }

    @Override
    protected void setEntityId(RoomType entity, Integer id) {
        entity.setId(id);
    }

	@Override
	protected RoomType convertDtoToEntity(RoomType dto) {
		return dto;
	}

	@Override
	protected RoomType convertEntityToDto(RoomType entity) {
		return entity;
	}


	@Secured({"ROLE_USER"})
	@GetMapping("/daily/{daily}")
	public ResponseEntity<List<RoomType>> findByDailyOrderByQt(@PathVariable Float daily){
		return ResponseEntity.ok(convertListToDto(service.findByDailyOrderByQt(daily))); 
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/daily-between/{dailyMin}/{dailyMax}")
	public ResponseEntity<List<RoomType>> findByDailyBetweenOrderByQt(@PathVariable Float dailyMin, @PathVariable Float dailyMax ){
		return ResponseEntity.ok(convertListToDto(service.findByDailyBetweenOrderByQt(dailyMin,dailyMax))); 
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/desc/{desc}")
	public ResponseEntity<List<RoomType>> findByDescriptionContainingIgnoreCaseOrderByDaily(@PathVariable String desc){
		return ResponseEntity.ok(convertListToDto(service.findByDescriptionContainingIgnoreCaseOrderByDaily(desc))); 
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/qt/{qt}")
	public ResponseEntity<List<RoomType>> findByQtOrderByDaily(@PathVariable Integer qt){
		return ResponseEntity.ok(convertListToDto(service.findByQtOrderByDaily(qt))); 
	}

}