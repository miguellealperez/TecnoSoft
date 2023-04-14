/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Servicios;

import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import com.TecnoSoftpruebas.dev.repositorios.UsuariosRepositorio;
import java.util.List;
import java.util.Optional;
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
    public UsuariosServicio(UsuariosRepositorio usuariosRepositorio) {
        this.usuariosRepositorio = usuariosRepositorio;
    }

    public List<UsuariosEntidad> listarUsuarios() {
        return usuariosRepositorio.findAll();
    }

    public UsuariosEntidad crearUsuario(UsuariosEntidad usuario) {
        //return usuariosRepositorio.save(usuario);
        // Verificar si el usuario ya existe
        if (usuariosRepositorio.findById(usuario.getUsuarioID()).isPresent()) {
            throw new UsuarioExistenteException("El usuario ya está registrado");
        }

        // Si el usuario no existe, crearlo
        return usuariosRepositorio.save(usuario);
    }

    public class UsuarioExistenteException extends RuntimeException {

        public UsuarioExistenteException(String mensaje) {
            super(mensaje);
        }
    }
    
    public Optional<UsuariosEntidad> buscarPorID(UsuariosEntidad usuario){
        return usuariosRepositorio.findById(usuario.getUsuarioID());
    }

    public void eliminarUsuarioPorId(Long usuarioId) {
        usuariosRepositorio.deleteById(usuarioId);
    }
    
    public void eliminarUsuario(UsuariosEntidad usuario) {
        usuariosRepositorio.delete(usuario);
    }
    
    public UsuariosEntidad authenticateUser(String correo, String contrasenia) {
        UsuariosEntidad usuario = usuariosRepositorio.findByCorreo(correo);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }
}
