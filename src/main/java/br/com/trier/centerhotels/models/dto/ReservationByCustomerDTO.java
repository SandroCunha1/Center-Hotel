package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationByCustomerDTO extends ReservationAbstractDTO {
	private Integer customerId;
	private String customerName;
	private String customerUser;
	private String customerCpf;
	
	
	public ReservationByCustomerDTO(Integer id, Integer roomId, String roomDesc, Float roomDaily, String dateInit,
			String dateFin, Integer customerId, String customerName, String customerUser, String customerCpf) {
		super(id, roomId, roomDesc, roomDaily, dateInit, dateFin);
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerUser = customerUser;
		this.customerCpf = customerCpf;
	}
	
	
}
