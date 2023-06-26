package br.com.trier.centerhotels.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity (name = "hotel")
public class Hotel extends User {
	
	@Column (name = "descr")
	private String description;
	
	@Column (name = "cnpj")
	@NotNull
	@NotBlank
	private String cnpj;
	
	public Hotel(Integer id, String name, String email, Integer cep, Integer phone, String user, String password, String description, String cnpj) {
		super(id, name, email, cep, phone, user, password);
		this.description = description;
		this.cnpj = cnpj;
	}
	
}
