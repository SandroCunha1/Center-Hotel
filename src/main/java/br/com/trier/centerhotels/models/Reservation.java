package br.com.trier.centerhotels.models;

import java.time.ZonedDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import br.com.trier.centerhotels.models.dto.ReservationAbstractDTO;
import br.com.trier.centerhotels.models.dto.ReservationByCustomerDTO;
import br.com.trier.centerhotels.models.dto.ReservationByEmployeeDTO;
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

	public Reservation(ReservationAbstractDTO dto, HotelRoom room, Employee employee, Customer customer) {
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.room = room;
		this.customer = customer;
		this.employee = employee;
	}

	public Reservation(ReservationAbstractDTO dto, HotelRoom room, Customer customer) {
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.room = room;
		this.customer = customer;
	}

	public Reservation(ReservationAbstractDTO dto, HotelRoom room, Employee employee) {
		this.dateInit = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.dateFin = DateUtils.dateBrToZoneDate(dto.getDateInit());
		this.room = room;
		this.employee = employee;
	}

	public ReservationAbstractDTO toDto() {
		if (getEmployee() == null) {
			return new ReservationByCustomerDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
					getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
					DateUtils.zoneDateToBrDate(getDateFin()), 
					getCustomer().getId(), getCustomer().getName(), getCustomer().getUser(), getCustomer().getCpf());
		}	
		if (getCustomer() == null) {
			return new ReservationByEmployeeDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
					getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
					DateUtils.zoneDateToBrDate(getDateFin()),
					getEmployee().getId() , getEmployee().getName(), getEmployee().getUser(), getEmployee().getCpf());
		}
		return new ReservationDTO(getId(), getRoom().getId(), getRoom().getType().getDescription(),
				getRoom().getType().getDaily(), DateUtils.zoneDateToBrDate(getDateInit()),
				DateUtils.zoneDateToBrDate(getDateFin()), 
				getCustomer().getId(), getCustomer().getName(), getCustomer().getUser(), getCustomer().getCpf(),
				getEmployee().getId() , getEmployee().getName(), getEmployee().getUser(), getEmployee().getCpf());
	}
}
