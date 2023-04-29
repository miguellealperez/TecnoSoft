/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Servicios;

import com.TecnoSoftpruebas.dev.entidades.EstadoTipo;
import com.TecnoSoftpruebas.dev.entidades.ProductosEntidad;
import com.TecnoSoftpruebas.dev.repositorios.ProductosRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel
 */
@Service
public class ProductosServicio {

    private final ProductosRepositorio productosrepositorio;

    public ProductosServicio(ProductosRepositorio productosRepositorio) {
        this.productosrepositorio = productosRepositorio;
    }

    public List<ProductosEntidad> ListarProductos() {
        return productosrepositorio.findAll();
    }

    public ProductosEntidad CrearProducto(ProductosEntidad producto) {
        if (productosrepositorio.findByproductoNombre(producto.getProductoNombre().trim()) != null) {
            throw new ProductoExixstenteExcepcion("Producto existe por nombre [" + producto.getProductoNombre().trim() + "]");
        }
        producto.setProductoEstado(EstadoTipo.ACTIVO);
        return productosrepositorio.save(producto);
    }

    public class ProductoExixstenteExcepcion extends RuntimeException {

        public ProductoExixstenteExcepcion(String mensaje) {
            super(mensaje);
        }
    }
    
    public void GuardarProducto(ProductosEntidad productoEditado){
        //productoEditado.setProductoID(Long.MIN_VALUE);
        productoEditado.setProductoNombre(productoEditado.getProductoNombre());
        productoEditado.setProductoTipo(productoEditado.getProductoTipo());
        productoEditado.setProductoDescripcion(productoEditado.getProductoDescripcion());
        productoEditado.setProductoCosto(productoEditado.getProductoCosto());
        productoEditado.setProductoValor(productoEditado.getProductoValor());
        productoEditado.setProductoEstado(productoEditado.getProductoEstado());
        productosrepositorio.save(productoEditado);
    }   
    
    public void EliminarProductoPorID(Long productoeliminar){
        productosrepositorio.deleteById(productoeliminar);
    }
    
    public Optional<ProductosEntidad> buscarProductoporID(Long productobuscado){
        return productosrepositorio.findById(productobuscado);
    }
    
    public ProductosEntidad buscarPorNombreIDno(ProductosEntidad product){
        return productosrepositorio.findByProductoNombreAndProductoIDNot(product.getProductoNombre(), product.getProductoID());
    }
}
