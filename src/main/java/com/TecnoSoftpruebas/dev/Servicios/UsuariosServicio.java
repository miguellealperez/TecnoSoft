/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TecnoSoftpruebas.dev.Servicios;

import com.TecnoSoftpruebas.dev.entidades.GastosEntidad;
import com.TecnoSoftpruebas.dev.entidades.UsuariosEntidad;
import com.TecnoSoftpruebas.dev.repositorios.UsuariosRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 *
 * @author Miguel
 */
@Service
public class UsuariosServicio {

    private UsuariosRepositorio usuariosRepositorio;

    public UsuariosServicio(UsuariosRepositorio usuariosRepositorio) {
        this.usuariosRepositorio = usuariosRepositorio;
    }

    public List<UsuariosEntidad> listarUsuarios() {
        return usuariosRepositorio.findAll();
    }

    public UsuariosEntidad crearUsuario(UsuariosEntidad usuario) {
        // Verificar si el usuario ya existe
        if (usuariosRepositorio.findById(usuario.getUsuarioID()).isPresent()) {
            throw new UsuarioExistenteException("Usuario ya registrado por el ID " + usuario.getUsuarioID());
        }

        // Verificar si el correo electrónico ya está registrado
        if (usuariosRepositorio.findByCorreo(usuario.getCorreo()) != null) {
            throw new UsuarioExistenteException("El correo electrónico ya está registrado");
        }

        // Si el usuario y correo electrónico no existen, crear el usuario
        return usuariosRepositorio.save(usuario);
    }

    public void guardarUsuario(UsuariosEntidad usuarioEditado) {
        usuarioEditado.setNombre(usuarioEditado.getNombre());
        usuarioEditado.setCorreo(usuarioEditado.getCorreo());
        usuarioEditado.setContrasenia(usuarioEditado.getContrasenia());
        usuarioEditado.setRolID(usuarioEditado.getRolID());
        usuariosRepositorio.save(usuarioEditado);
    }

    public class UsuarioExistenteException extends RuntimeException {

        public UsuarioExistenteException(String mensaje) {
            super(mensaje);
        }
    }

    // public UsuariosEntidad buscarPorIDnoOptional(UsuariosEntidad usuario) {
    // return usuariosRepositorio.findByIDENTIF(usuario.getUsuarioID());
    // }
    //
    public Optional<UsuariosEntidad> buscarPorID(UsuariosEntidad usuario) {
        return usuariosRepositorio.findById(usuario.getUsuarioID());
    }

    public Optional<UsuariosEntidad> buscarPorIDid(Long usuario) {
        return usuariosRepositorio.findById(usuario);
    }

    public UsuariosEntidad buscarPorCorreo(UsuariosEntidad usuario) {
        return usuariosRepositorio.findByCorreo(usuario.getCorreo());
    }

    public UsuariosEntidad buscarPorCorreoYIdSin(UsuariosEntidad usuario) {
        return usuariosRepositorio.findByCorreoAndUsuarioIDNotOrUsuarioIDNot(usuario.getCorreo(),
                usuario.getUsuarioID(), usuario.getUsuarioID());
        /*
         * En este caso, se está utilizando el método
         * findByCorreoAndUsuarioIDNotOrUsuarioIDNot, que incluye dos condiciones en la
         * consulta:
         * - La primera condición busca entidades por correo electrónico y excluye la
         * entidad con el ID especificado (UsuarioIDNot).
         * - La segunda condición excluye la entidad con el ID especificado
         * (UsuarioIDNot), de manera que se obtiene la entidad con un correo electrónico
         * diferente al especificado y con un ID diferente al que se está editando.
         * 
         * Al incluir ambas condiciones con el operador "OR", se obtiene la entidad que
         * cumple cualquiera de las dos condiciones.
         * 
         * Es importante mencionar que esta convención de nomenclatura de Spring Data
         * JPA solo funciona si la entidad tiene un
         * atributo id (o un atributo con un nombre diferente, pero que se haya
         * especificado con la anotación @Id), ya que se basa
         * en la convención de que el identificador de la entidad es un atributo llamado
         * "id". Si la entidad no tiene un atributo id, se debe
         * utilizar otra convención de nomenclatura para indicar el atributo que se
         * utilizará como identificador en la consulta.
         */
    }

    public UsuariosEntidad buscarPorCorreoSin(UsuariosEntidad usuario) {
        return usuariosRepositorio.findByCorreoAndUsuarioIDNot(usuario.getCorreo(), usuario.getUsuarioID());
        /*
         * En este caso, el método que se está utilizando es
         * findByCorreoAndUsuarioIDNot, que sigue la convención de nomenclatura
         * de Spring Data JPA. El "IDNot" en este caso indica que se buscará una entidad
         * por correo electrónico (findByCorreo) y
         * que se excluirá la entidad con el ID especificado (UsuarioIDNot). De esta
         * manera, se obtendrá la entidad que tenga el correo electrónico
         * especificado pero que no sea la misma que se está editando.
         * 
         * Es importante mencionar que esta convención de nomenclatura de Spring Data
         * JPA solo funciona si la entidad tiene un
         * atributo id (o un atributo con un nombre diferente, pero que se haya
         * especificado con la anotación @Id), ya que se basa
         * en la convención de que el identificador de la entidad es un atributo llamado
         * "id". Si la entidad no tiene un atributo id, se debe
         * utilizar otra convención de nomenclatura para indicar el atributo que se
         * utilizará como identificador en la consulta.
         */
    }

    public UsuariosEntidad buscarPorIdSin(UsuariosEntidad usuario) {
        return usuariosRepositorio.findByUsuarioIDNot(usuario.getUsuarioID());
        /*
         * En este caso, el método que se está utilizando es findByUsuarioIDNot, que
         * sigue la convención de nomenclatura
         * de Spring Data JPA. El "IDNot" en este caso indica que se buscará una entidad
         * excluyendo la entidad con el ID especificado.
         * De esta manera, se obtendrá la entidad que tenga un ID diferente al que se
         * está editando.
         * 
         * Es importante mencionar que esta convención de nomenclatura de Spring Data
         * JPA solo funciona si la entidad tiene un
         * atributo id (o un atributo con un nombre diferente, pero que se haya
         * especificado con la anotación @Id), ya que se basa
         * en la convención de que el identificador de la entidad es un atributo llamado
         * "id". Si la entidad no tiene un atributo id, se debe
         * utilizar otra convención de nomenclatura para indicar el atributo que se
         * utilizará como identificador en la consulta.
         */
    }

    public void eliminarUsuarioPorId(Long usuarioId) {
        usuariosRepositorio.deleteById(usuarioId);
    }

    public void eliminarUsuario(UsuariosEntidad usuario) {
        usuariosRepositorio.delete(usuario);
    }

    public UsuariosEntidad authenticateUser(String correo, String contrasenia) {
        UsuariosEntidad usuario = usuariosRepositorio.findByCorreo(correo);
        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            return usuario;
        }
        return null;
    }

    public void consultarGastos(Long usuarioID) {
        // Obtener un usuario por su ID
        Optional<UsuariosEntidad> usuario = usuariosRepositorio.findById(usuarioID);

        // Obtener los gastos asociados a ese usuario
        Set<GastosEntidad> gastos = usuario.get().getGastosEntidad();

        // Recorrer los gastos y mostrar su información
        for (GastosEntidad gasto : gastos) {
            System.out.println("Gasto: " + gasto.getNombreGasto());
            System.out.println("Tipo de gasto: " + gasto.getTipoGasto());
            System.out.println("Fecha del gasto: " + gasto.getFechaGasto());
            System.out.println("Valor del gasto: " + gasto.getValorGasto());
            System.out.println("Descripción del gasto: " + gasto.getDescripcionGasto());
            System.out.println("Usuario que hizo el gasto: " + gasto.getUsuariosEntidad().getNombre());
        }

    }
}
