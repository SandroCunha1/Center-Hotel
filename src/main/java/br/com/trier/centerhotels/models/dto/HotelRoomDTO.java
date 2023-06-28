package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HotelRoomDTO {

	private Integer id;
	private Integer num;
	private Integer typeId;
	private Float daily;
	private Integer qt;
	private String descr;
	private Integer hotelId;
	private String hotelName;
	private String cnpj;
}
