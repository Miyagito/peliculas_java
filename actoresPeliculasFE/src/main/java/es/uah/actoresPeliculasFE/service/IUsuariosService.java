package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Actor;
import es.uah.actoresPeliculasFE.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuariosService {
    Page<Usuario> buscarTodos(String query, String tipo, Pageable pageable);
    List<Usuario> buscarTodos();
    Usuario buscarUsuarioPorId(Integer id);
    List <Usuario> buscarUsuarioPorNombre(String nombre);
    List <Usuario> buscarUsuarioPorCorreo(String correo);
    void guardarOEditarUsuario(Usuario actor);
    void eliminarUsuario(Integer id);
}
