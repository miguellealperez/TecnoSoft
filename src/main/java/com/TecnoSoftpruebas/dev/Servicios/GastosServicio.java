package com.TecnoSoftpruebas.dev.Servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.TecnoSoftpruebas.dev.repositorios.GastosRepositorio;
import com.TecnoSoftpruebas.dev.entidades.GastosEntidad;

@Service
public class GastosServicio {
    private final GastosRepositorio gastosRepositorio;

    public GastosServicio(GastosRepositorio gastosRepositorio) {
        this.gastosRepositorio = gastosRepositorio;
    }

    public List<GastosEntidad> ListarGastos(){
        return gastosRepositorio.findAll();
    }

    public GastosEntidad CrearGasto(GastosEntidad gasto){
        return gastosRepositorio.save(gasto);
    }

    
}
