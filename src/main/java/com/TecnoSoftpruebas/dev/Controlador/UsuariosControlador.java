/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Controlador;

import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Miguel
 */
@Controller
public class UsuariosControlador {
    private UsuariosServicio usuariosServicio;
    
    @Autowired
    public UsuariosControlador(UsuariosServicio usuariosServicio){
        this.usuariosServicio = usuariosServicio;
    }
    
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model){
        List<UsuariosEntidad> usuarios = usuariosServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
}
