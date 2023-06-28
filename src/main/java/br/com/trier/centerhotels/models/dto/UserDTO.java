package br.com.trier.centerhotels.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDTO  {

	private Integer id;
	private String cep;
	private String name;
	private String email;
	private String user;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private Integer phone;

}
