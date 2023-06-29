package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationDTO extends ReservationAbstractDTO {
	private Integer customerId;
	private String customerName;
	private String customerUser;
	private String customerCpf;
	private Integer employeeId;
	private String employeeName;
	private String employeeUser;
	private String employeeCpf;
	
	public ReservationDTO(Integer id, Integer roomId, String roomDesc, Float roomDaily, String dateInit, String dateFin,
			Integer customerId, String customerName, String customerUser, String customerCpf, Integer employeeId,
			String employeeName, String employeeUser, String employeeCpf) {
		super(id, roomId, roomDesc, roomDaily, dateInit, dateFin);
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerUser = customerUser;
		this.customerCpf = customerCpf;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeUser = employeeUser;
		this.employeeCpf = employeeCpf;
	}
	
	
	
	
}
