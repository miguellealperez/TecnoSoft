package com.TecnoSoftpruebas.dev.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "gastos")
public class GastosEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_gasto")
    private Long numeroGasto;

    @Column(name = "tipo_gasto")
    private String tipoGasto;

    @Column(name = "nombre_gasto")
    private String nombreGasto;

    @Column(name = "fecha_gasto")
    @Temporal(TemporalType.DATE)
    private Date fechaGasto;
    
    @Column(name = "descripcion_gasto")
    private String descripcionGasto;

    @Column(name = "valor_gasto")
    private float valorGasto;

    @ManyToOne
    @JoinColumn(name = "usuario_ID")
    private UsuariosEntidad usuariosEntidad;

    public Long getNumeroGasto() {
        return numeroGasto;
    }

    public void setNumeroGasto(Long numeroGasto) {
        this.numeroGasto = numeroGasto;
    }

    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }

    public String getNombreGasto() {
        return nombreGasto;
    }

    public void setNombreGasto(String nombreGasto) {
        this.nombreGasto = nombreGasto;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getDescripcionGasto() {
        return descripcionGasto;
    }

    public void setDescripcionGasto(String descripcionGasto) {
        this.descripcionGasto = descripcionGasto;
    }

    public float getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(float valorGasto) {
        this.valorGasto = valorGasto;
    }

    public UsuariosEntidad getUsuariosEntidad() {
        return usuariosEntidad;
    }

    public void setUsuariosEntidad(UsuariosEntidad usuariosEntidad) {
        this.usuariosEntidad = usuariosEntidad;
    }

    
}
