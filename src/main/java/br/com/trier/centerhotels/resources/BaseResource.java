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

public abstract class BaseResource<T, ID, S extends BaseServiceImpl<T, ID>> {

    protected final S service;

    public BaseResource(S service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<T> insert(@RequestBody T entity) {
        return ResponseEntity.ok(service.insert(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<T>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        setEntityId(entity, id);
        return ResponseEntity.ok(service.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    protected abstract void setEntityId(T entity, ID id);
}
