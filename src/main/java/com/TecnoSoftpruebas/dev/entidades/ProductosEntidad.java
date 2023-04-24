/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "productos")
public class ProductosEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_ID")
    private Long productoID;
    
    @Column(name = "producto_nombre")
    private String productoNombre;
    
    @Column(name = "producto_costo")
    private float productoCosto;
    
    @Column(name = "producto_valor")
    private float productoValor;
    
    @Column(name = "producto_tipo")
    private String productoTipo;
    
    @Column(name = "producto_descripcion")
    private String productoDescripcion;

    public Long getProductoID() {
        return productoID;
    }

    public void setProductoID(Long productoID) {
        this.productoID = productoID;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public float getProductoCosto() {
        return productoCosto;
    }

    public void setProductoCosto(float productoCosto) {
        this.productoCosto = productoCosto;
    }

    public float getProductoValor() {
        return productoValor;
    }

    public void setProductoValor(float productoValor) {
        this.productoValor = productoValor;
    }

    public String getProductoTipo() {
        return productoTipo;
    }

    public void setProductoTipo(String productoTipo) {
        this.productoTipo = productoTipo;
    }

    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    public void setProductoDescripcion(String productoDescripcion) {
        this.productoDescripcion = productoDescripcion;
    }
}