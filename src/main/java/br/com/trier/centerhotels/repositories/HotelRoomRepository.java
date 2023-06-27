package br.com.trier.centerhotels.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.users.Hotel;

public interface HotelRoomRepository extends JpaRepository<HotelRoom, Integer> {

	List<HotelRoom> findByHotel(Hotel hotel);
	List<HotelRoom> findByType(RoomType type);
	List<HotelRoom> findByNum(RoomType type);
	List<HotelRoom> findByHotelAndType(Hotel hotel, RoomType type);
}
