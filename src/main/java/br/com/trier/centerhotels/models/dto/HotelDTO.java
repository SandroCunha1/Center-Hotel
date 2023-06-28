package br.com.trier.centerhotels.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HotelDTO extends UserDTO{

	private String cnpj;
	private String descr;
	
	public HotelDTO(Integer id, String cep, String name, String email, String user, String password, Integer phone, String cnpj, String descr) {
		super(id, cep, name, email, user, password, phone);
		this.cnpj = cnpj;
		this.descr = descr;
	}
}
