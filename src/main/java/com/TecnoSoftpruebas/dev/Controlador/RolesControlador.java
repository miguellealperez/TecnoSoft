package com.TecnoSoftpruebas.dev.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.TecnoSoftpruebas.dev.Servicios.RolesServicios;

@Controller
public class RolesControlador {
    
    @Autowired
    private RolesServicios rolesServicios;

    public RolesControlador(RolesServicios rolesServicios) {
        this.rolesServicios = rolesServicios;
    }

    @GetMapping(value="/roles")
    public String VerRoles(Model model){
        model.addAttribute("roles", rolesServicios.ListarRoles());
        return "roles";
    }

}
