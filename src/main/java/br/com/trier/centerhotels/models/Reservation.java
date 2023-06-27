package br.com.trier.centerhotels.models;

import java.time.ZonedDateTime;

import br.com.trier.centerhotels.models.users.Customer;
import br.com.trier.centerhotels.models.users.Employee;
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
@Entity(name="reservation")
public class Reservation {

	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	private Integer id;
	
	@Column(name = "init")
	@NotNull
	private ZonedDateTime dateInit;
	
	@Column(name = "fin")
	@NotNull
	private ZonedDateTime dateFin;
	
	@Column (name = "payment")
	private Float payment;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "room_id")
	private HotelRoom room;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
