package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.RoomType;

public interface RoomTypeService extends BaseCrudService<RoomType> {
	List<RoomType> findByDailyOrderByQt(Float daily);
	List<RoomType> findByDailyBetweenOrderByQt(Float dailyMin, Float dailyMax);
	List<RoomType> findByDescriptionContainingIgnoreCaseOrderByDaily(String desc);
	List<RoomType> findByQtOrderByDaily(Integer qt);
}
