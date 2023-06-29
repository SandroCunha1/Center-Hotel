package br.com.trier.centerhotels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	List<RoomType> findByDailyOrderByQt(Float daily);
	List<RoomType> findByDailyBetweenOrderByQt(Float dailyMin, Float dailyMax);
	List<RoomType> findByDescriptionContainingIgnoreCaseOrderByDaily(String desc);
	List<RoomType> findByQtOrderByDaily(Integer qt);
}
