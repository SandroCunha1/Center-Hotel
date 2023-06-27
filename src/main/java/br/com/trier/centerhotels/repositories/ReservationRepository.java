package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}
