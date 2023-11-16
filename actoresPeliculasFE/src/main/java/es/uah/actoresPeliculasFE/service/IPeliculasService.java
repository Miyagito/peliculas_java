package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Actor;
import es.uah.actoresPeliculasFE.model.Pelicula;
import es.uah.actoresPeliculasFE.model.PeliculaRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPeliculasService {

    Page<Pelicula> buscarPeliculas(String query, String tipo, Pageable pageable);

    Page<Pelicula> buscarTodos(Pageable pageable);

    List<Actor> buscarActoresPorIds(List<Integer> ids);

    Pelicula buscarPeliculaPorId(Integer id);

    List<Pelicula> buscarPeliculasPorTitulo(String titulo);

    List<Pelicula> buscarPeliculasPorGenero(String genero);

    List<Pelicula> buscarPeliculasPorActor(String actor);

    List<Pelicula> buscarPeliculasPorDirector(String director);

    List<Actor> buscarActoresEnPelicula(Integer id);

    void guardarPelicula(PeliculaRequest pelicula);

    void eliminarPelicula(Integer id);

    void actualizarPelicula(Pelicula pelicula);
}

