package br.com.trier.centerhotels.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.repositories.RoomTypeRepository;
import br.com.trier.centerhotels.services.RoomTypeService;

@Service
public class RoomTypeServiceImpl extends BaseServiceImpl<RoomType, Integer> implements RoomTypeService {

    @Autowired
    private RoomTypeRepository repository;

    @Override
    protected JpaRepository<RoomType, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(RoomType entity) {
        return entity.getId();
    }

	
}