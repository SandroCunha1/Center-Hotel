package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.models.users.Hotel;

public interface HotelRoomService extends BaseCrudService<HotelRoom> {
	List<HotelRoom> findByHotel(Hotel hotel);
	List<HotelRoom> findByType(RoomType type);
	List<HotelRoom> findByNum(RoomType type);
	List<HotelRoom> findByHotelAndType(Hotel hotel, RoomType type);
}
