package br.com.trier.centerhotels.services;

import java.util.List;

import br.com.trier.centerhotels.models.users.Hotel;

public interface HotelService extends BaseCrudService<Hotel> {
	List<Hotel> findByUserStartsWithIgnoreCase(String user);
	List<Hotel> findByNameStartsWithIgnoreCase(String name);
	List<Hotel> findByCnpjStartsWith(String cnpj);
	List<Hotel> findByCepStartsWith(String cep);
	List<Hotel> findByEmailStartsWithIgnoreCase(String email);
	List<Hotel> findByCnpjStartsWithAndNameStartsWithIgnoreCase(String cnpj, String name);
	List<Hotel> findByDescriptionContainingIgnoreCase(String descr);
}
