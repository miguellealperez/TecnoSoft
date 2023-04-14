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

    public UsuariosEntidad findByCorreo(String correo);
}
