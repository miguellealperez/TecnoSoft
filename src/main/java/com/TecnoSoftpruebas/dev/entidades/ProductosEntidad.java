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

    @Column(name = "producto_estado")
    private EstadoTipo productoEstado;

    @Column(name = "producto_iva")
    private String productoIva;

    @Column(name = "producto_iva_valor")
    private float productoIvaValor;

    @Column(name = "producto_procentaje_utilidad")
    private float porcentajeUtilidad;

    public float getPorcentajeUtilidad() {
        return porcentajeUtilidad;
    }

    public void setPorcentajeUtilidad(float porcentajeUtilidad) {
        this.porcentajeUtilidad = porcentajeUtilidad;
    }

    public String getProductoIva() {
        return productoIva;
    }

    public void setProductoIva(String productoIva) {
        this.productoIva = productoIva;
    }

    public float getProductoIvaValor() {
        return productoIvaValor;
    }

    public void setProductoIvaValor(float productoIvaValor) {
        this.productoIvaValor = productoIvaValor;
    }

    public EstadoTipo getProductoEstado() {
        return productoEstado;
    }

    public void setProductoEstado(EstadoTipo productoEstado) {
        this.productoEstado = productoEstado;
    }

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
