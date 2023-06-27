package br.com.trier.centerhotels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.RoomType;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
	List<RoomType> findByDaily(Float daily);
}
