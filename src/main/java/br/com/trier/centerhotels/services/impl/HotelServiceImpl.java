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
		return findByTemplate("findByUserStartsWithIgnoreCase",
				"Nenhum hotel/usuário registrado com :",
				user);
	}

	@Override
	public List<Hotel> findByNameStartsWithIgnoreCase(String name) {
		return findByTemplate("findByNameStartsWithIgnoreCase",
				"Nenhum hotel/nome registrado com :",
				name);
	}

	@Override
	public List<Hotel> findByCnpjStartsWith(String cnpj) {
		return findByTemplate("findByCnpjStartsWith",
				"Nenhum hotel/Cnpj registrado com :",
				cnpj);
	}

	@Override
	public List<Hotel> findByCepStartsWith(String cep) {
		return findByTemplate("findByCepStartsWith",
				"Nenhum hotel/Cep registrado com :",
				cep);
	}

	@Override
	public List<Hotel> findByEmailStartsWithIgnoreCase(String email) {
		return findByTemplate("findByEmailStartsWithIgnoreCase",
				"Nenhum hotel/Email registrado com :",
				email);
	}

	@Override
	public List<Hotel> findByCnpjStartsWithAndNameStartsWithIgnoreCase(String cnpj, String name) {
		return findByTemplateTwo("findByCnpjStartsWithAndNameStartsWithIgnoreCase",
				"Nenhum hotel/cnpj e hotel/nome registrado com :",
				cnpj, name);
	}

	@Override
	public List<Hotel> findByDescriptionContainingIgnoreCase(String descr) {
		return findByTemplate("findByDescriptionContainingIgnoreCase",
				"Nenhuma descrição contendo :",
				descr);
	}
}
