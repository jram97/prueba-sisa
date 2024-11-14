package com.jram.web_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jram.web_service.entity.OrdenCompra;
import com.jram.web_service.repository.OrdenCompraRepository;

@Service
public class OrdenCompraServiceImpl implements CrudService<OrdenCompra> {

    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<OrdenCompra> getAll() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public OrdenCompra saveOrUpdate(OrdenCompra t) {
        return ordenCompraRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        ordenCompraRepository.delete(ordenCompraRepository.findById(id).get());
    }

}
