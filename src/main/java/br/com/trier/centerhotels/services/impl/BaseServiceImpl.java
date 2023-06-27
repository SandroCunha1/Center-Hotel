package br.com.trier.centerhotels.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;

public abstract class BaseServiceImpl <T, ID> {

	    protected abstract JpaRepository<T, ID> getRepository();

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
