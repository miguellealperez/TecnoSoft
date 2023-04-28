/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.entidades;



import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_ID")
    private RolesEntidad rolesEntidad;

    public RolesEntidad getRolesEntidad() {
        return rolesEntidad;
    }

    public void setRolesEntidad(RolesEntidad rolesEntidad) {
        this.rolesEntidad = rolesEntidad;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_ID")
    private Set<GastosEntidad> GastosEntidad;
    
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isPresente() {
        return getCorreo() != null;
    }

    public Set<GastosEntidad> getGastosEntidad() {
        return GastosEntidad;
    }

    public void setGastosEntidad(Set<GastosEntidad> gastosEntidad) {
        GastosEntidad = gastosEntidad;
    }

}
