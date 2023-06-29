package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationAbstractDTO {
	private Integer id;
	private Integer roomId;
	private String roomDesc;
	private Float roomDaily;
	private String dateInit;
	private String dateFin;
}
