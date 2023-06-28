package br.com.trier.centerhotels.models.users;

import br.com.trier.centerhotels.models.dto.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity (name = "customer")
@NoArgsConstructor
@ToString
public class Customer extends User {
	
	@Column (name = "cpf")
	@NotNull
	@NotBlank
	private String cpf;
	
	public Customer(Integer id, String name, String email, String cep, Integer phone, String user, String password, String cpf) {
		super(id, name, email, cep, phone, user, password);
		this.cpf = cpf;
	}
	
	public Customer(CustomerDTO dto) {
		this(dto.getId(), dto.getName(), dto.getEmail(), dto.getCep(), dto.getPhone(), dto.getUser(), dto.getPassword(), dto.getCpf());
	}
	
	public CustomerDTO toDTO() {
		return new CustomerDTO(getId(), getCep(), getName(), getEmail(), getUser(), getPassword(), getPhone(), getCpf());
	}

}
