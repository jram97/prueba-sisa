package com.jram.web_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jram.web_service.entity.Producto;
import com.jram.web_service.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements CrudService<Producto> {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto saveOrUpdate(Producto t) {
        return productoRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        productoRepository.delete(productoRepository.findById(id).get());
    }

}
