package br.com.trier.centerhotels.services.impl;

import java.util.List;

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

	@Override
	public List<Hotel> findByUserStartsWithIgnoreCase(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByNameStartsWithIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByCnpjStartsWith(Integer cnpj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByCepStartsWith(Integer cep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByEmailStartsWithIgnoreCase(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByCnpjStartsWithAndNameStartsWithIgnoreCase(Integer cpf, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> findByDescriptionContainingIgnoreCase(String descr) {
		// TODO Auto-generated method stub
		return null;
	}
}
