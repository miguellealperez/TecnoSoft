/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.repositorios;

import com.TecnoSoftpruebas.dev.entidades.ProductosEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Miguel
 */

public interface ProductosRepositorio extends JpaRepository<ProductosEntidad, Long> {

    ProductosEntidad findByproductoNombre(String productoNombre);

    public ProductosEntidad findByProductoNombreAndProductoIDNot(String productoNombre, Long productoID);
    
}
