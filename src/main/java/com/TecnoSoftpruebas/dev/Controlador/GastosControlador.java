package com.TecnoSoftpruebas.dev.Controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.TecnoSoftpruebas.dev.Servicios.GastosServicio;
import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.GastosEntidad;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GastosControlador {
    @Autowired
    private GastosServicio gastosServicio;

    @Autowired
    private UsuariosServicio usuariosServicio;

    public GastosControlador(GastosServicio gastosServicio) {
        this.gastosServicio = gastosServicio;
    }

    @GetMapping(value="/contable/{usuarioID}")
    public String verContable(@PathVariable("usuarioID") Long usuarioID, Model model) {
        List<GastosEntidad> gastos = gastosServicio.ListarGastos();
        usuariosServicio.consultarGastos(usuarioID);
        model.addAttribute("gastos", gastos);
        return "contables";
    }
    
    @GetMapping(value = "/contable/{usuarioID}/nuevo")
    public String nuevoGastoContable(@PathVariable("usuarioID") Long usuarioID, Model model){
        GastosEntidad gasto = new GastosEntidad();
        model.addAttribute("gasto", gasto);
        model.addAttribute("usuarioID", usuarioID);
        return "nuevogasto";
    }

    @PostMapping(value = "/contable/{usuarioID}/crear")
    public String crearGastoContable(@PathVariable("usuarioID") Long usuarioID, Model model, @ModelAttribute("gasto") GastosEntidad gasto){
        Optional<UsuariosEntidad> usuario = usuariosServicio.buscarPorIDid(usuarioID);
        gasto.setUsuariosEntidad(usuario.get());
        gastosServicio.CrearGasto(gasto);
        return "redirect:/contable/{usuarioID}";
    }
}
