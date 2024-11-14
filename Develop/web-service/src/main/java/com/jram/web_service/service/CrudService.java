package com.jram.web_service.service;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();

    T saveOrUpdate(T t);

    void delete(Long id);

}
