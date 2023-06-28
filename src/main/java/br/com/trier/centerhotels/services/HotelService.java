package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.users.Hotel;

public interface HotelService extends BaseCrudService<Hotel> {
	List<Hotel> findByUserStartsWithIgnoreCase(String user);
	List<Hotel> findByNameStartsWithIgnoreCase(String name);
	List<Hotel> findByCnpjStartsWith(Integer cnpj);
	List<Hotel> findByCepStartsWith(Integer cep);
	List<Hotel> findByEmailStartsWithIgnoreCase(String email);
	List<Hotel> findByCnpjStartsWithAndNameStartsWithIgnoreCase(Integer cpf, String name);
	List<Hotel> findByDescriptionContainingIgnoreCase(String descr);
}
