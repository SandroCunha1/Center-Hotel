package br.com.trier.centerhotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.users.Hotel;
import br.com.trier.centerhotels.repositories.HotelRepository;
import br.com.trier.centerhotels.services.HotelService;

@Service
public class HotelServiceImpl extends BaseServiceImpl<Hotel, Integer> implements HotelService {

    @Autowired
    private HotelRepository repository;

    @Override
    protected JpaRepository<Hotel, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(Hotel entity) {
        return entity.getId();
    }
}
