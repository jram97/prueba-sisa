package com.jram.web_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jram.web_service.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
