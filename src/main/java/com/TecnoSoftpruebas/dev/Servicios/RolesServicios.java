package com.TecnoSoftpruebas.dev.Servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TecnoSoftpruebas.dev.entidades.RolesEntidad;
import com.TecnoSoftpruebas.dev.repositorios.RolesRepositorio;

@Service
public class RolesServicios {
    private final RolesRepositorio rolesRepositorio;

    public RolesServicios(RolesRepositorio rolesRepositorio) {
        this.rolesRepositorio = rolesRepositorio;
    }

    public List<RolesEntidad> ListarRoles(){
        return rolesRepositorio.findAll();
    }

    public RolesEntidad CrearRol(RolesEntidad rol){
        return rolesRepositorio.save(rol);
    }

    public RolesEntidad buscarPorId(Long rolID) {
        return rolesRepositorio.findById(rolID).orElse(null);
    }
}
