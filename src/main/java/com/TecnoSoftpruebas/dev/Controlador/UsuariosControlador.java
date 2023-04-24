/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Controlador;

import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Miguel
 */
@Controller
public class UsuariosControlador {

    @Autowired
    private UsuariosServicio usuariosServicio;

    public UsuariosControlador(UsuariosServicio usuariosServicio) {
        this.usuariosServicio = usuariosServicio;
    }

    // @GetMapping("/usuarios")
    // public String listarUsuarios(@PathVariable Long usuarioid, Model model) {
    // List<UsuariosEntidad> usuarios = usuariosServicio.listarUsuarios();
    // model.addAttribute("usuarios", usuarios);
    // model.addAttribute("usuarioID", usuarioid);
    // // Puedes agregar aquí el código necesario para buscar al usuario por su ID y
    // hacer alguna acción
    // return "usuarios";
    // }
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<UsuariosEntidad> usuarios = usuariosServicio.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    // @GetMapping("/usuarios/{usuarioid}/nuevo")
    // public String crearUsuario(@PathVariable("usuarioid") Long usuarioid, Model
    // model, HttpServletRequest request) {
    // UsuariosEntidad usuario = new UsuariosEntidad();
    // model.addAttribute("usuario", usuario);
    // String refererURL = request.getHeader("Referer");
    // return "redirect:" + refererURL;
    // }
    // @GetMapping("/usuarios/{usuarioid}/nuevo")
    // public String crearUsuario(@PathVariable("usuarioid") Long usuarioid, Model
    // model) {
    // UsuariosEntidad usuario = new UsuariosEntidad();
    // model.addAttribute("usuario", usuario);
    // model.addAttribute("usuarioID", usuarioid);
    // return "usuarionuevo";
    // }

    @GetMapping("/usuarios/nuevo")
    public String crearUsuario(Model model) {
        UsuariosEntidad usuario = new UsuariosEntidad();
        model.addAttribute("usuario", usuario);
        return "usuarionuevo";
    }

    // @PostMapping("/usuarios/crear/{usuarioid}")
    // public String guardarUsuario(@ModelAttribute("usuario") UsuariosEntidad
    // usuario, Model model, @PathVariable("usuarioid") Long usuarioid) {
    // model.addAttribute("usuarioID", usuarioid);
    // try {
    // usuariosServicio.crearUsuario(usuario);
    // return "redirect:/usuarios/" + usuarioid;
    // } catch (DataIntegrityViolationException e) {
    // model.addAttribute("error", "El usuario ya está registrado.");
    // return "usuarionuevo";
    // } catch (UsuariosServicio.UsuarioExistenteException e) {
    // model.addAttribute("error", "El usuario ya está registrado.");
    // return "usuarionuevo";
    // }
    // }
    @PostMapping("/usuarios/crear")
    public String guardarUsuario(@ModelAttribute("usuario") UsuariosEntidad usuario, Model model) {
        try {
            usuariosServicio.crearUsuario(usuario);
            return "redirect:/usuarios";
        } catch (UsuariosServicio.UsuarioExistenteException e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "usuarionuevo";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "DATA ID/correo ya está registrado.");
            System.out.println("2");
            return "usuarionuevo";
        }
    }

    // @GetMapping("/usuarios/eliminar/{usuarioID}")
    // public String eliminarUsuario(@PathVariable("usuarioID") Long usuarioID,
    // @PathVariable("usuarioid") Long usuarioid,
    // Model model) {
    // usuariosServicio.eliminarUsuarioPorId(usuarioID);
    // model.addAttribute("usuarioID", usuarioid);
    // return "redirect:/usuarios/";
    // }
    // @GetMapping("/usuarios/eliminar/{usuarioID}/{usuarioid}")
    // public String eliminarUsuario(@PathVariable("usuarioID") Long usuarioID,
    // @PathVariable("usuarioid") Long usuarioid, Model model
    // ) {
    // model.addAttribute("usuarioID", usuarioid);
    // usuariosServicio.eliminarUsuarioPorId(usuarioID);
    // return "redirect:/usuarios/" + usuarioid;
    // }
    //
    @GetMapping("/usuarios/eliminar/{usuarioID}")
    public String eliminarUsuario(@PathVariable("usuarioID") Long usuarioID) {
        usuariosServicio.eliminarUsuarioPorId(usuarioID);
        return "redirect:/usuarios";
    }

    // @PostMapping("/login")
    // public String submitLoginForm
    // (@ModelAttribute("usuario")
    // UsuariosEntidad usuario, BindingResult bindingResult
    // , HttpSession session, Model model
    //
    // ) {
    // UsuariosEntidad authenticatedUser =
    // usuariosServicio.authenticateUser(usuario.getCorreo(),
    // usuario.getContrasenia());
    // if (authenticatedUser != null) {
    // List<GrantedAuthority> authorities =
    // AuthorityUtils.createAuthorityList("ROLE_USER");
    // User user = new User(authenticatedUser.getCorreo(),
    // authenticatedUser.getContrasenia(), authorities);
    // session.setAttribute("user", user);
    // return "redirect:/usuarios";
    // } else {
    // bindingResult.rejectValue("contrasenia", "error.user", "Contraseña
    // incorrecta");
    // model.addAttribute("loginError", true);
    // return "login";
    // }
    // }
    @GetMapping("/usuarios/editar/{usuarioID}")
    public String mostrarFormularioEditarUsuario(@PathVariable("usuarioID") Long usuarioID, Model model) {
        // Obtener el usuario por ID
        Optional<UsuariosEntidad> usuario = usuariosServicio.buscarPorIDid(usuarioID);
        // Validar si existe el usuario
        if (usuario.isPresent()) {
            model.addAttribute("usuario", usuario.get());
            return "usuarioseditar"; // retornar la vista para editar usuario
        } else {
            return "redirect:/usuarios"; // redireccionar a la lista de usuarios si el usuario no existe
        }
    }

    @PostMapping("/usuarios/editar")
    public String guardarUsuarioEditado(@Valid @ModelAttribute("usuario") UsuariosEntidad usuarioEditado,
            BindingResult bindingResult, Model model) {
        try {
            // Validar si hay errores de validación
            if (bindingResult.hasErrors()) {
                model.addAttribute("error", "Por favor corrija los errores en el formulario");
                return "usuarioseditar";
            } else {
                // Validar que no exista otro usuario con el mismo correo electrónico y número
                // de identificación
                // UsuariosEntidad usuarioExistente =
                // usuariosServicio.buscarPorCorreoYIdSin(usuarioEditado);
                // UsuariosEntidad usuarioExistenteid =
                // usuariosServicio.buscarPorCorreoYIdSin(usuarioEditado);
                UsuariosEntidad usuarioExistente = usuariosServicio.buscarPorCorreoSin(usuarioEditado);
                // System.out.println("uedi1: " + usuarioEditado.getUsuarioID());
                // System.out.println("--1: " + usuarioExistente.getCorreo());
                if (usuarioExistente != null && usuarioExistente.getUsuarioID().equals(usuarioEditado.getUsuarioID())) {
                    model.addAttribute("error", "Ya existe otro usuario con este número de identificación");
                    // System.out.println("uedi2: " + usuarioEditado.getUsuarioID());
                    return "usuarioseditar";
                }

                // System.out.println("--: " + usuarioExistente.getCorreo());
                // UsuariosEntidad usuarioExistenteIdentificacion =
                // usuariosServicio.buscarPorIDSin(usuarioEditado);
                if (usuarioExistente != null && usuarioExistente.getCorreo().equals(usuarioEditado.getCorreo())) {
                    model.addAttribute("error", "Ya existe otro usuario con este correo electrónico");
                    // System.out.println("uedi3: " + usuarioEditado.getCorreo());
                    // System.out.println("--: " + usuarioExistente.getCorreo());
                    // System.out.println("--2: " + usuarioExistente.getUsuarioID());

                    return "usuarioseditar";
                }
                // Guardar el usuario editado
                usuariosServicio.guardarUsuario(usuarioEditado);
                return "redirect:/usuarios"; // redireccionar a la lista de usuarios
            }
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "El usuario ya está registrado.");
            return "usuarionuevo";
        } catch (UsuariosServicio.UsuarioExistenteException e) {
            model.addAttribute("error", "El id/correo ya está registrado.");
            return "usuarionuevo";
        }
    }

}
