package br.com.trier.centerhotels.models.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelRoomsDTO {

	private Integer hotelId;
	private String hotelName;
	private Integer roomsQt;
	private List<HotelRoomDTO> rooms;
}
