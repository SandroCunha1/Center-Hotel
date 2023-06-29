package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationDTO {
	private Integer id;
	private Integer roomId;
	private String roomDesc;
	private Float roomDaily;
	private String dateInit;
	private String dateFin;
	private Integer customerId;
	private String customerName;
	private String customerUser;
	private String customerCpf;
	private Integer employeeId;
	private String employeeName;
	private String employeeUser;
	private String employeeCpf;
	
	
	
	
	
}
