package br.com.trier.centerhotels.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeDTO extends UserDTO {

	private String cpf;
	private Integer hotelId;
	private String hotelName;
	private String hotelCnpj;
	
	public EmployeeDTO(Integer id, String cep, String name, String email, String user, String password, Integer phone, String cpf, Integer hotelId, String hotelName, String hotelCnpj, String roles) {
		super(id, cep, name, email, user, password, roles, phone);
		this.cpf = cpf;
		this.hotelId = hotelId;
		this.hotelCnpj = hotelCnpj;
		this.hotelName = hotelName;
	}
}
