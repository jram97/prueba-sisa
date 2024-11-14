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

import com.jram.web_service.entity.OrdenCompra;
import com.jram.web_service.service.CrudService;

@RestController
@RequestMapping(path = "/orden")
@CrossOrigin(origins = "*")
public class OrdenCompraController {

    @Autowired
    CrudService<OrdenCompra> oCrudService;

    @GetMapping
    public ResponseEntity<List<OrdenCompra>> getAll() {
        try {
            List<OrdenCompra> orden = new ArrayList<OrdenCompra>();

            oCrudService.getAll().forEach(orden::add);

            if (orden.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(orden, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OrdenCompra> create(@RequestBody OrdenCompra orden) {
        try {
            OrdenCompra savedOrden = oCrudService.saveOrUpdate(orden);
            return new ResponseEntity<>(savedOrden, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            oCrudService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
    

}
