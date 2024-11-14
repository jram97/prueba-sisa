package com.jram.web_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jram.web_service.entity.DetalleOrdenCompra;
import com.jram.web_service.entity.OrdenCompra;
import com.jram.web_service.entity.Producto;
import com.jram.web_service.repository.DetalleOrdenCompraRepository;
import com.jram.web_service.repository.OrdenCompraRepository;
import com.jram.web_service.repository.ProductoRepository;

@Repository
public class DetalleOrdenCompraServiceImple implements CrudService<DetalleOrdenCompra> {

    @Autowired
    DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    @Autowired
    OrdenCompraRepository ordenCompraRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<DetalleOrdenCompra> getAll() {
        return detalleOrdenCompraRepository.findAll();
    }

    @Override
    public DetalleOrdenCompra saveOrUpdate(DetalleOrdenCompra t) {

        double total = 0.0;
        Producto producto = productoRepository.findById(t.getProducto().getId()).get();

        total = t.getCantidad() * producto.getPrecio();

        OrdenCompra ordenCompra = ordenCompraRepository.save(new OrdenCompra(null, total));

        t.setProducto(producto);
        t.setTotal(total);
        t.setOrdenCompra(ordenCompra);

        return detalleOrdenCompraRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        detalleOrdenCompraRepository.save(detalleOrdenCompraRepository.findById(id).get());
    }

}
