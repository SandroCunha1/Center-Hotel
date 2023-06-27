package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.HotelRoom;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {

}
