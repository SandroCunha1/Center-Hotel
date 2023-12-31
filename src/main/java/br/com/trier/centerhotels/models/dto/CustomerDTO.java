package br.com.trier.centerhotels.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDTO extends UserDTO  {

	private String cpf;
	
	public CustomerDTO(Integer id, String cep, String name, String email, String user, String password, Integer phone, String cpf, String roles) {
		super(id, cep, name, email, user, password, roles, phone);
		this.cpf = cpf;
	}
	
	
	
}
