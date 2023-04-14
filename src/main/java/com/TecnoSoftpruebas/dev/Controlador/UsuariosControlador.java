/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Controlador;

import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Miguel
 */
@Controller
public class UsuariosControlador {

    @Autowired
    private UsuariosServicio usuariosServicio;

    @Autowired
    public UsuariosControlador(UsuariosServicio usuariosServicio) {
        this.usuariosServicio = usuariosServicio;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<UsuariosEntidad> usuarios = usuariosServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String crearUsuario(Model model) {
        UsuariosEntidad usuario = new UsuariosEntidad();
        model.addAttribute("usuario", usuario);
        return "usuarionuevo";
    }

    @PostMapping("/usuarios/crear")
    public String guardarUsuario(@ModelAttribute("usuario") UsuariosEntidad usuario, Model model) {
        try {
            usuariosServicio.crearUsuario(usuario);
            return "redirect:/usuarios";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "El usuario ya está registrado.");
            return "usuarionuevo";
        } catch (UsuariosServicio.UsuarioExistenteException e) {
            model.addAttribute("error", "El usuario ya está registrado.");
            return "usuarionuevo";
        }
    }

    @GetMapping("/usuarios/eliminar/{usuarioID}")
    public String eliminarUsuario(@PathVariable("usuarioID") Long usuarioID) {
        usuariosServicio.eliminarUsuarioPorId(usuarioID);
        return "redirect:/usuarios";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        UsuariosEntidad usuario = new UsuariosEntidad();
        model.addAttribute("usuario", usuario);
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("usuario") UsuariosEntidad usuario, BindingResult bindingResult, HttpSession session, Model model) {
        UsuariosEntidad authenticatedUser = usuariosServicio.authenticateUser(usuario.getCorreo(), usuario.getContrasenia());
        if (authenticatedUser != null) {
            List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
            User user = new User(authenticatedUser.getCorreo(), authenticatedUser.getContrasenia(), authorities);
            session.setAttribute("user", user);
            return "redirect:/usuarios";
        } else {
            bindingResult.rejectValue("contrasenia", "error.user", "Contraseña incorrecta");
            model.addAttribute("loginError", true);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
