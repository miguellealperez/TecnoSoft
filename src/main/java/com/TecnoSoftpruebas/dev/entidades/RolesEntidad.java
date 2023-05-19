package com.TecnoSoftpruebas.dev.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RolesEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_ID")
    private Long rolID;

    @Column(name = "nombre_rol")   
    private String nombreRol;

    // @OneToMany(fetch = FetchType.LAZY)
    // @Column(name = "usuario_ID")
    // private UsuariosEntidad usuariosEntidad;

    public Long getRolID() {
        return rolID;
    }

    public void setRolID(Long rolID) {
        this.rolID = rolID;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    // public UsuariosEntidad getUsuariosEntidad() {
    //     return usuariosEntidad;
    // }

    // public void setUsuariosEntidad(UsuariosEntidad usuariosEntidad) {
    //     this.usuariosEntidad = usuariosEntidad;
    // }

    
}
