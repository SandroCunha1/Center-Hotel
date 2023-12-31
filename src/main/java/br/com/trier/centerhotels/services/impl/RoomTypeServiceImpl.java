package br.com.trier.centerhotels.services.impl;
import java.util.List;

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


	@Override
	public List<RoomType> findByDailyOrderByQt(Float daily) {
		return findByTemplate("findByDailyOrderByQt",
				"Nenhum quarto com valor diário de :",
				daily);
	}

	@Override
	public List<RoomType> findByDescriptionContainingIgnoreCaseOrderByDaily(String desc) {
		return findByTemplate("findByDescriptionContainingIgnoreCaseOrderByDaily",
				"Nenhum tipo com descrição contendo :",
				desc);
	}

	@Override
	public List<RoomType> findByQtOrderByDaily(Integer qt) {
		return findByTemplate("findByQtOrderByDaily",
				"Nenhum quarto com quantidade máxima de pessoas igual á :",
				qt);
	}

	@Override
	public List<RoomType> findByDailyBetweenOrderByQt(Float dailyMin, Float dailyMax) {
		return findByTemplateTwo("findByDailyBetweenOrderByQt",
				"Nenhum quarto com valor diário entre :",
				dailyMin, dailyMax);
	}

	
}