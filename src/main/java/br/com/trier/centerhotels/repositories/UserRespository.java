package br.com.trier.centerhotels.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.trier.centerhotels.models.users.User;

public interface UserRespository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	User findByUser(String user);
}
