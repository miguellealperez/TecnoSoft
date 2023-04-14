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
@Table(name = "usuarios")
public class UsuariosEntidad {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_ID")
    private Long usuarioID;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "correo")
    private String correo;
    
    @Column(name = "contrasenia")
    private String contrasenia;
    
    @Column(name = "rol_ID")
    private Long rolID;
    
    public UsuariosEntidad(){}

    public Long getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Long usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getRolID() {
        return rolID;
    }

    public void setRolID(Long rolID) {
        this.rolID = rolID;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
   
}
