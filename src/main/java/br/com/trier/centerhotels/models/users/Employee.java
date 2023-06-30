package br.com.trier.centerhotels.models.users;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.trier.centerhotels.models.dto.EmployeeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity (name = "employee")
@NoArgsConstructor
@ToString
public class Employee extends User {

	@Column (name = "cpf")
	@NotNull
	@NotBlank
	private String cpf;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "hotel_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hotel hotel;
	
	public Employee(Integer id, String name, String email, String cep, Integer phone, String user, String password, String cpf, Hotel hotel, String roles) {
		super(id, name, email, cep, phone, user, password, roles);
		this.cpf = cpf;
		this.hotel = hotel;
	}
	
	public Employee(EmployeeDTO dto, Hotel hotel) {
		this(dto.getId(), dto.getName(), dto.getEmail(), dto.getCep(), dto.getPhone(), dto.getUser(), dto.getPassword(), dto.getCpf(), hotel, dto.getRoles());
	}
	
	public EmployeeDTO toDTO() {
		return new EmployeeDTO(getId(), getCep(), getName(), getEmail(), getUser(), getPassword(), getPhone(), getCpf(), getHotel().getId(), getHotel().getName(), getHotel().getCnpj(), getRoles());
	}
}
