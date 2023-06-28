package br.com.trier.centerhotels.services.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.trier.centerhotels.models.users.User;
import br.com.trier.centerhotels.repositories.UserRespository;
import br.com.trier.centerhotels.services.exceptions.IntegrityViolation;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;

public abstract class BaseServiceImpl<T, ID> {

	protected abstract JpaRepository<T, ID> getRepository();

	@Autowired
	private UserRespository userRepository;

	
	protected void validateUser(User entity) {
		User existingUser = userRepository.findByEmail(entity.getEmail());
		if (existingUser != null && !existingUser.getId().equals(entity.getId())) {
			throw new IntegrityViolation("Email já cadastrado");
		}
		existingUser = userRepository.findByUser(entity.getUser());
		if (existingUser != null && !existingUser.getId().equals(entity.getId())) {
			throw new IntegrityViolation("Usuário já existe");
		}
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	protected <T> List<T> findByTemplate(String attribute, String errorMsg, Object value) {
		try {
			Method method = getRepository().getClass().getMethod(attribute, value.getClass());
			List<T> resultList = (List<T>) method.invoke(getRepository(), value);
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Atributo inválido: " + attribute);
		}

		throw new ObjectNotFound("%s %s".formatted(errorMsg, value));
	}

	@SuppressWarnings({ "unchecked", "hiding" })
	protected <T> List<T> findByTemplateTwo(String attribute, String errorMsg, Object value, Object value2) {
		try {
			Method method = getRepository().getClass().getMethod(attribute, value.getClass(), value2.getClass());
			List<T> resultList = (List<T>) method.invoke(getRepository(), value, value2);
			if (resultList != null && !resultList.isEmpty()) {
				return resultList;
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new IllegalArgumentException("Atributo inválido: " + attribute);
		}

		throw new ObjectNotFound("%s %s e %s".formatted(errorMsg, value, value2));
	}

	public T findById(ID id) {
		Optional<T> entity = getRepository().findById(id);
		return entity.orElseThrow(() -> new ObjectNotFound("O objeto com ID %s não existe".formatted(id)));
	}

	public T insert(T entity) {
		return getRepository().save(entity);
	}

	public List<T> listAll() {
		List<T> entities = getRepository().findAll();
		if (entities.isEmpty()) {
			throw new ObjectNotFound("Nenhum objeto cadastrado");
		}
		return entities;
	}

	public T update(T entity) {
		findById(getEntityId(entity));
		return getRepository().save(entity);
	}

	public void delete(ID id) {
		T entity = findById(id);
		getRepository().delete(entity);
	}

	protected abstract ID getEntityId(T entity);
}
