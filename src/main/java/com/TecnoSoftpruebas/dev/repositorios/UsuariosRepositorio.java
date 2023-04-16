/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.repositorios;

import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author Miguel
 */
public interface UsuariosRepositorio extends JpaRepository<UsuariosEntidad, Long> {
    UsuariosEntidad findByNombre(String nombre);

    UsuariosEntidad findByCorreo(String correo);
    
    //UsuariosEntidad findByIDENTIF(Long id);

    public UsuariosEntidad findByCorreoAndUsuarioIDNot(String correo, Long usuarioID);

    //public UsuariosEntidad findByNumeroIdentificacionAndUsuarioIDNot(Long usuarioID, Long usuarioID0);

    public UsuariosEntidad findByUsuarioIDNot(Long usuarioID);

    public UsuariosEntidad findByCorreoAndUsuarioIDNotOrUsuarioIDNot(String correo, Long usuarioID, Long usuarioID0);
}
