package br.com.trier.centerhotels.models;

import java.time.ZonedDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import br.com.trier.centerhotels.models.dto.ReservationDTO;
import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;
import br.com.trier.centerhotels.utils.DateUtils;
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
@Entity(name = "reservation")
public class Reservation {

	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "init")
	@NotNull
	private ZonedDateTime dateInit;

	@Column(name = "fin")
	@NotNull
	private ZonedDateTime dateFin;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "room_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private HotelRoom room;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public Reservation(ReservationDTO dto, HotelRoom room, Employee employee, Customer customer) {
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateFin());
		this.room = room;
		this.customer = customer;
		this.employee = employee;
	}

	public Reservation(ReservationDTO dto, Customer customer, @NotNull HotelRoom room) {
		super();
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateFin());
		this.customer = customer;
		this.room = room;
	}

	public Reservation(ReservationDTO dto, Employee employee, @NotNull HotelRoom room) {
		super();
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateFin());
		this.employee = employee;
		this.room = room;
	}
	

	public ReservationDTO toDto() {
		
		if (getCustomer() == null) {
			return new ReservationDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
					getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
					DateUtils.zoneDateToBrDate(getDateFin()),
					null, null, null, null,
					getEmployee().getId(), getEmployee().getName(), getEmployee().getUser(), getEmployee().getCpf());
		}

		if (getEmployee() == null) {
			return new ReservationDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
					getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
					DateUtils.zoneDateToBrDate(getDateFin()), 
					getCustomer().getId(), getCustomer().getName(), getCustomer().getUser(), getCustomer().getCpf(),
					null, null, null, null);
		}
		

		return new ReservationDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
				getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
				DateUtils.zoneDateToBrDate(getDateFin()), 
				getCustomer().getId(), getCustomer().getName(), getCustomer().getUser(), getCustomer().getCpf(), 
				getEmployee().getId(), getEmployee().getName(), getEmployee().getUser(), getEmployee().getCpf());
	}

}
