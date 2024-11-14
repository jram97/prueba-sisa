package com.jram.web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

	/*
	 * CREATE OR REPLACE PROCEDURE upsert_producto(
    p_id IN INT,
    p_nombre IN VARCHAR2,
    p_precio IN NUMBER
) AS
BEGIN
    MERGE INTO t_producto tp
    USING dual d
    ON (tp.id = p_id)
    WHEN MATCHED THEN
        UPDATE SET tp.nombre = p_nombre, tp.precio = p_precio
    WHEN NOT MATCHED THEN
        INSERT (id, nombre, precio)
        VALUES (p_id, p_nombre, p_precio);
END upsert_producto;
/


	 * 
	 * 
	 */

}
