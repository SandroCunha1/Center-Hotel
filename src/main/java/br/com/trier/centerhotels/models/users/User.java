package br.com.trier.centerhotels.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "cep")
	private Integer cep;
	
	@Column (name = "user")
	@NotNull
	@NotBlank
	private String user;
	
	@Column (name = "password")
	private String password;
}
