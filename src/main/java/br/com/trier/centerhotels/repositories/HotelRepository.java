package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.models.users.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
