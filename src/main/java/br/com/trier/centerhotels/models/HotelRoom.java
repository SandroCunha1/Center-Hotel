package br.com.trier.centerhotels.models;

import br.com.trier.centerhotels.models.users.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="hotel_room")
public class HotelRoom {

	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	
	@Column (name = "num")
	@NotNull
	private Integer num;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "type_id")
	private RoomType type;
}
