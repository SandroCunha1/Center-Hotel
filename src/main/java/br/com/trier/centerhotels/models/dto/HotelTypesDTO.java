package br.com.trier.centerhotels.models.dto;

import java.util.List;

import br.com.trier.centerhotels.models.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HotelTypesDTO {

	private Integer hotelId;
	private String hotelName;
	private Integer typesQt;
	private List<RoomType> types;
}
