package br.com.trier.centerhotels.resources;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.trier.centerhotels.services.impl.BaseServiceImpl;

public abstract class BaseResource<T, ID,D ,  S extends BaseServiceImpl<T, ID>> {

    protected final S service;

    public BaseResource(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<D> insert(@RequestBody D entityDTO) {
    	T entity = convertDtoToEntity(entityDTO);
        return ResponseEntity.ok(convertEntityToDto(service.insert(entity)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable ID id) {
        return ResponseEntity.ok(convertEntityToDto(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<D>> listAll() {
        return ResponseEntity.ok(convertListToDto(service.listAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable ID id, @RequestBody D entityDTO) {
    	T entity = convertDtoToEntity(entityDTO);
        setEntityId(entity, id);
        return ResponseEntity.ok(convertEntityToDto(service.update(entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    protected List<D> convertListToDto(List<T> list ){
    	return list.stream().map(entity -> convertEntityToDto(entity)).toList();
    }

    protected abstract void setEntityId(T entity, ID id);
    
    protected abstract T convertDtoToEntity(D dto);

    protected abstract D convertEntityToDto(T entity);

}
