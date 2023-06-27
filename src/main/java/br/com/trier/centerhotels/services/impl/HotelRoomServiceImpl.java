package br.com.trier.centerhotels.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.trier.centerhotels.models.HotelRoom;
import br.com.trier.centerhotels.repositories.HotelRoomRepository;
import br.com.trier.centerhotels.services.HotelRoomService;

@Service
public class HotelRoomServiceImpl extends BaseServiceImpl<HotelRoom, Integer> implements HotelRoomService {

    @Autowired
    private HotelRoomRepository repository;

    @Override
    protected JpaRepository<HotelRoom, Integer> getRepository() {
        return repository;
    }

    @Override
    protected Integer getEntityId(HotelRoom entity) {
        return entity.getId();
    }
}
