package br.com.trier.centerhotels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.trier.centerhotels.models.users.User;

public interface UserRespository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	User findByUser(String user);
}
