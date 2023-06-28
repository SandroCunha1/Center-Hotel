package br.com.trier.centerhotels.models.users;
import br.com.trier.centerhotels.models.dto.HotelDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity (name = "hotel")
@NoArgsConstructor
@ToString
public class Hotel extends User {
	
	@Column (name = "descr")
	private String description;
	
	@Column (name = "cnpj")
	@NotNull
	@NotBlank
	private String cnpj;
	
	public Hotel(Integer id, String name, String email, String cep, Integer phone, String user, String password, String description, String cnpj) {
		super(id, name, email, cep, phone, user, password);
		this.description = description;
		this.cnpj = cnpj;
	}
	
	public Hotel(HotelDTO dto) {
		this(dto.getId(), dto.getName(), dto.getEmail(), dto.getCep(), dto.getPhone(), dto.getUser(), dto.getPassword(), dto.getCnpj(), dto.getDescr());
	}
	
	public HotelDTO toDTO() {
		return new HotelDTO(getId(), getCep(), getName(), getEmail(), getUser(), getPassword(), getPhone(), getCnpj(), getDescription());
	}
	
}
