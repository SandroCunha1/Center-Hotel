package br.com.trier.centerhotels.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomTypeDTO {

	  private Integer id;
	    private String description;
	    private Integer quantity;
	    private Float dailyRate;

	    public RoomTypeDTO(Integer id, String description, Integer quantity, Float dailyRate) {
	        this.id = id;
	        this.description = description;
	        this.quantity = quantity;
	        this.dailyRate = dailyRate;
	    }
}
