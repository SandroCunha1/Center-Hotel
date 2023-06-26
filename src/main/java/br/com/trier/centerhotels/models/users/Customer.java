package br.com.trier.centerhotels.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity (name = "customer")
public class Customer extends User {
	
	@Column (name = "cpf")
	@NotNull
	@NotBlank
	private String cpf;
	
	public Customer(Integer id, String name, String email, Integer cep, Integer phone, String user, String password, String cpf) {
		super(id, name, email, cep, phone, user, password);
		this.cpf = cpf;
	}

}
