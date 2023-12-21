package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuariosService {
    Page<Usuario> buscarUsuarios(String query, String tipo, Pageable pageable);
    Page<Usuario> buscarTodos(String query, String tipo, Pageable pageable);
    Usuario buscarUsuarioPorId(Integer id);
    void guardarOEditarUsuario(Usuario actor);
    void eliminarUsuario(Integer id);
}
