package com.jram.web_service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jram.web_service.entity.Producto;
import com.jram.web_service.repository.ProductoRepository;
import com.jram.web_service.service.CrudService;

@RestController
@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    CrudService<Producto> proCrudService;
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        try {
            List<Producto> productos = new ArrayList<Producto>();

            proCrudService.getAll().forEach(productos::add);

            if (productos.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(productos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        try {
            Producto savedProducto = proCrudService.saveOrUpdate(producto);
            return new ResponseEntity<>(savedProducto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            proCrudService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") Long id) {
        Optional<Producto> existingProductOptional = productoRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            return new ResponseEntity<>(existingProductOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
