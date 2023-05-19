/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Controlador;

import com.TecnoSoftpruebas.dev.Servicios.ProductosServicio;
import com.TecnoSoftpruebas.dev.entidades.EstadoTipo;
import com.TecnoSoftpruebas.dev.entidades.ProductosEntidad;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Miguel
 */
@Controller
public class ProductosControlador {
    @Autowired
    private ProductosServicio productosServicio;
    
    public ProductosControlador(ProductosServicio productosServicio) {
        this.productosServicio = productosServicio;
    }
    
    @GetMapping("/productos")
    public String ListarProductos(Model model){
        List<ProductosEntidad> productos = productosServicio.ListarProductos();
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        return "productos";
    }
    
    @GetMapping("productos/nuevo")
    public String CrearProducto(Model model){
        ProductosEntidad producto = new ProductosEntidad();
        model.addAttribute("producto", producto);
        return "productonuevo";
    }
    
    @PostMapping("productos/crear")
    public String GuardarProducto(@ModelAttribute("producto") ProductosEntidad producto, Model model){
        try {
            productosServicio.CrearProducto(producto);
            return "redirect:/productos";
        } catch (ProductosServicio.ProductoExixstenteExcepcion e) {
            model.addAttribute("error", e.getMessage());
            return "productonuevo";
        }
    }
    
    @GetMapping("productos/eliminar/{productoID}")
    public String EliminarProducto(@PathVariable("productoID") Long ProductoID){
        productosServicio.EliminarProductoPorID(ProductoID);
        return "redirect:/productos";
    }
    
    @GetMapping("productos/editar/{productoID}")
    public String mostrarFormProducto(@PathVariable("productoID") Long ProductoID, Model model){
        Optional<ProductosEntidad> producto = productosServicio.buscarProductoporID(ProductoID);
        if(producto.isPresent()){
            model.addAttribute("producto", producto);
            model.addAttribute("estados", EstadoTipo.values());
            return "productoeditar";
        }else {
            return "redirect:/productos";
        }
    }
    
    @PostMapping("productos/editar")
    public String GuardarProductoEditado(@Valid @ModelAttribute("producto") ProductosEntidad productoEditado, BindingResult bindingResult, Model model){
        try {
            if(bindingResult.hasErrors()){
                model.addAttribute("error", "Por favor corrija los errores en el formulario");
                return "productoeditar";
            }else {
                ProductosEntidad productoExistente = productosServicio.buscarPorNombreIDno(productoEditado);

                if (productoExistente != null && productoExistente.getProductoNombre().equals(productoEditado.getProductoNombre())) {
                    model.addAttribute("error", "Ya existe otro producto con el nombre [" + productoExistente.getProductoNombre() + "]");
                    //System.out.println("uedi2: " + usuarioEditado.getUsuarioID());
                    return "productoeditar";
                }
                
                productosServicio.GuardarProducto(productoEditado);
                return "redirect:/productos"; 
            }
            
        } catch (ProductosServicio.ProductoExixstenteExcepcion e) {
            model.addAttribute("error", "Ya existe otro producto con este nombre");
            return "productoeditado";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "El usuario ya está registrado.");
            return "productoeditado";
        }
    }
}
