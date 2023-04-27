package com.TecnoSoftpruebas.dev.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TecnoSoftpruebas.dev.entidades.RolesEntidad;

public interface RolesRepositorio extends JpaRepository<RolesEntidad, Long>{
    
}
