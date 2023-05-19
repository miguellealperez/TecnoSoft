package com.TecnoSoftpruebas.dev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TecnoSoftpruebas.dev.entidades.GastosEntidad;

public interface GastosRepositorio extends JpaRepository<GastosEntidad, Long> {
    
}
