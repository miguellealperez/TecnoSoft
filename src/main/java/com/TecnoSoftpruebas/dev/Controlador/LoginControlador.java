/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Controlador;

import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Miguel
 */
@Controller
public class LoginControlador {

    @Autowired
    private final UsuariosServicio usuariosServicio;

    public LoginControlador(UsuariosServicio usuariosServicio) {
        this.usuariosServicio = usuariosServicio;
    }

    @RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
    public String showLoginForm(Model model
    ) {
        UsuariosEntidad usuario = new UsuariosEntidad();
        model.addAttribute("usuario", usuario);
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(
            @ModelAttribute("usuario") UsuariosEntidad usuario,
            BindingResult bindingResult,
            HttpSession session,
            Model model
    ) {
        UsuariosEntidad authenticatedUser = usuariosServicio.authenticateUser(usuario.getCorreo(), usuario.getContrasenia());
        if (authenticatedUser != null) {
            // Obtener el rol del usuario autenticado
            String rol = authenticatedUser.getRolID();

            // Redirigir al usuario a la vista correspondiente según su rol
            switch (rol) {
                case "Administrador" -> {
                    return "redirect:/usuarios";
                }
                case "Gerente" -> {
                    return "redirect:/gerente/" + authenticatedUser.getUsuarioID();
                }
                case "Contable" -> {
                    return "redirect:/contable/" + authenticatedUser.getUsuarioID();
                }
                default -> throw new IllegalArgumentException("Rol no válido");
            }
        } else {
            bindingResult.rejectValue("contrasenia", "error.user", "Contraseña incorrecta");
            model.addAttribute("loginError", "Contraseña incorrecta. Por favor, inténtalo de nuevo.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session
    ) {
        session.invalidate();
        return "redirect:/login";
    }

}
