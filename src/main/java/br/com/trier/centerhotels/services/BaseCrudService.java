package br.com.trier.centerhotels.services;

import java.util.List;

public interface BaseCrudService<T> {

    T findById(Integer id);
    
    T insert(T entity);
    
    List<T> listAll();
    
    T update(T entity);
    
    void delete(Integer id);
}
