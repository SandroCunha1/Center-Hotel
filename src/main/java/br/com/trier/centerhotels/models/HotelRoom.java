package br.com.trier.centerhotels.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.trier.centerhotels.models.dto.HotelRoomDTO;
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
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "hotel_room")
public class HotelRoom {

	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")

	private Integer id;

	@Column(name = "num")
	@NotNull
	private Integer num;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "hotel_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hotel hotel;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "type_id")
	private RoomType type;

	public HotelRoom(HotelRoomDTO dto, Hotel hotel, RoomType type) {
		this.id = dto.getId();
		this.num = dto.getNum();
		this.hotel = hotel;
		this.type = type;
	}

	public HotelRoomDTO toDto() {
		return new HotelRoomDTO(getId(), getNum(), getType().getId(), getType().getDaily(), getType().getQt(),
				getType().getDescription(), getHotel().getId(), getHotel().getName(), getHotel().getCnpj());
	}
}
