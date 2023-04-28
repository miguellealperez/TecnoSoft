///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
package com.TecnoSoftpruebas.dev;

import com.TecnoSoftpruebas.dev.Controlador.LoginControlador;
import com.TecnoSoftpruebas.dev.Servicios.RolesServicios;
import com.TecnoSoftpruebas.dev.Servicios.UsuariosServicio;
import com.TecnoSoftpruebas.dev.entidades.RolesEntidad;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import com.TecnoSoftpruebas.dev.repositorios.RolesRepositorio;
import com.TecnoSoftpruebas.dev.repositorios.UsuariosRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.mock.web.MockHttpSession;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class ControladorLoginTests {

    @Autowired
    private LoginControlador controladorLogin;

    @Autowired
    private UsuariosServicio usuariosServicio;

    // @Autowired
    // private UsuariosRepositorio usuariosRepositorios;

    @Autowired
    private RolesRepositorio rolesRepositorios;

    @Autowired
    private RolesServicios rolesServicios;
    
    @Test
    void crearRoles(){
        RolesEntidad roles = new RolesEntidad();
        roles.setNombreRol("Administrador");
        RolesEntidad crearRol = rolesRepositorios.save(roles);
        assert crearRol != null;
    }

    @Test
    void CrearUsuario(){
        // Crear un usuario de prueba TEST
        UsuariosEntidad usuario = new UsuariosEntidad();
        // RolesEntidad roles = new RolesEntidad();
        // roles.setNombreRol("Administrador");
        // rolesRepositorios.save(roles);
        usuario.setRolesEntidad(rolesServicios.buscarPorId(8L));
        usuario.setCorreo("p@p");
        usuario.setContrasenia("1234");
        usuario.setNombre("Pepito Perez");
        usuario.setUsuarioID(123L);
        UsuariosEntidad crearUsuario = usuariosServicio.crearUsuario(usuario);
        assert crearUsuario != null;
    }

    @Test
    void consultarUsuario(){
        
        assert usuariosServicio.buscarPorid(123L) != null;
    }



    /*// @Test
    // void testSubmitLoginForm() throws Exception {
    //     // Crear un usuario de prueba TEST
    //     UsuariosEntidad usuario = new UsuariosEntidad();
    //     RolesEntidad roles = new RolesEntidad();
    //     //roles.setRolID(1L);
    //     roles.setNombreRol("Administrador");
    //     rolesRepositorios.save(roles);
    //     usuario.setRolesEntidad(roles);
    //     usuario.setCorreo("a@a");
    //     usuario.setContrasenia("123");
    //     usuario.setNombre("Juan Carpio");
    //     // usuario.setRolID("Administrador");
    //     // usuario.setRolesEntidad(usuario.getRolesEntidad());
    //     usuario.setUsuarioID(123L);
    //     usuariosRepositorios.save(usuario);

    //     // Crear una sesión de prueba
    //     MockHttpSession sesion = new MockHttpSession();

    //     // Autenticar al usuario
    //     UsuariosEntidad usuarioAutenticado = usuariosServicio.authenticateUser(usuario.getCorreo(),
    //             usuario.getContrasenia());

    //     // Llamar al método submitLoginForm del controlador
    //     MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controladorLogin).build();
    //     mockMvc.perform(
    //             MockMvcRequestBuilders.post("/login")
    //                     .param("correo", usuario.getCorreo())
    //                     .param("contrasenia", usuario.getContrasenia())
    //                     .session(sesion))
    //             .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
    //             .andExpect(MockMvcResultMatchers.redirectedUrl("/usuarios"));
    // }*/

}
