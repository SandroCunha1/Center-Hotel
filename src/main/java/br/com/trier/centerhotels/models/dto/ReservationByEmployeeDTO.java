package br.com.trier.centerhotels.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationByEmployeeDTO extends ReservationAbstractDTO {
	private Integer employeeId;
	private String employeeName;
	private String employeeUser;
	private String employeeCpf;
	
	public ReservationByEmployeeDTO(Integer id, Integer roomId, String roomDesc, Float roomDaily, String dateInit,
			String dateFin, Integer employeeId, String employeeName, String employeeUser, String employeeCpf) {
		super(id, roomId, roomDesc, roomDaily, dateInit, dateFin);
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeUser = employeeUser;
		this.employeeCpf = employeeCpf;
	}
	
	
}
