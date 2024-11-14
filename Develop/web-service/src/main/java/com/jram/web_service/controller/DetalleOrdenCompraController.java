package com.jram.web_service.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.jram.web_service.entity.DetalleOrdenCompra;
import com.jram.web_service.service.CrudService;

@RestController
@RequestMapping(path = "/detalle")
@CrossOrigin(origins = "*")
public class DetalleOrdenCompraController {

    @Autowired
    CrudService<DetalleOrdenCompra> dCrudService;

    @GetMapping
    public ResponseEntity<List<DetalleOrdenCompra>> getAll() {

        try {
            List<DetalleOrdenCompra> detalles = new ArrayList<DetalleOrdenCompra>();

            dCrudService.getAll().forEach(detalles::add);

            if (detalles.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(detalles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<DetalleOrdenCompra> create(@RequestBody DetalleOrdenCompra detalle) {
        try {
            DetalleOrdenCompra savedDetalle = dCrudService.saveOrUpdate(detalle);
            return new ResponseEntity<>(savedDetalle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            dCrudService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
