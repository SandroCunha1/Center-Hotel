package br.com.trier.centerhotels.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity (name = "employee")
public class Employee extends User {

	@Column (name = "cpf")
	@NotNull
	@NotBlank
	private String cpf;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	public Employee(Integer id, String name, String email, Integer cep, Integer phone, String user, String password, String cpf, Hotel hotel) {
		super(id, name, email, cep, phone, user, password);
		this.cpf = cpf;
		this.hotel = hotel;
	}
}
