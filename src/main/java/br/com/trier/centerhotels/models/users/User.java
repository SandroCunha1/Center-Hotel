package br.com.trier.centerhotels.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user_parent")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "email")
	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@Column (name = "cep")
	@NotNull
	private String cep;
	
	@Column (name = "phone")
	private Integer phone;
	
	@Column (name = "tb_user")
	@NotNull
	@NotBlank
	private String user;
	
	@Column (name = "password")
	@NotNull
	@NotBlank
	private String password;
	
	@Column (name = "permission")
	@NotNull
	private String roles;
}
