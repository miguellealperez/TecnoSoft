/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Servicios;

import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import com.TecnoSoftpruebas.dev.repositorios.UsuariosRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Miguel
 */
@Service
public class UsuariosServicio {
    private UsuariosRepositorio usuariosRepositorio;
    
    @Autowired
    public UsuariosServicio(UsuariosRepositorio usuariosRepositorio){
        this.usuariosRepositorio = usuariosRepositorio;
    }
    
    public List<UsuariosEntidad> listarUsuarios() {
        return usuariosRepositorio.findAll();
    }
    
    public UsuariosEntidad crearUsuario(UsuariosEntidad usuario) {
        return usuariosRepositorio.save(usuario);
    }
}
