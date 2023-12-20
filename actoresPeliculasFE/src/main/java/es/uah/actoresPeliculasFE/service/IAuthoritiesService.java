package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Authorities;
import es.uah.actoresPeliculasFE.model.Usuario;

import java.util.List;

public interface IAuthoritiesService {
    List<Authorities> buscarTodos();
}
