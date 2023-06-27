package br.com.trier.centerhotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.Reservation;
import br.com.trier.centerhotels.repositories.ReservationRepository;
import br.com.trier.centerhotels.services.ReservationService;

@Service
public class ReservationServiceImpl extends BaseServiceImpl<Reservation, Integer> implements ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    protected JpaRepository<Reservation, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(Reservation entity) {
        return entity.getId();
    }
}
