package es.uah.actoresPeliculas.service;
import es.uah.actoresPeliculas.model.Actor;
import es.uah.actoresPeliculas.model.Pelicula;
import java.util.List;

public interface IPeliculasService {
    List<Pelicula> buscarTodos();

    Pelicula buscarPeliculaPorId(Integer idPelicula);

    List<Pelicula> buscarPeliculasPorTitulo(String titulo);

    List<Pelicula> buscarPeliculasPorAnno(Integer anno);

    List<Pelicula> buscarPeliculasPorDuracion(Integer duracion);

    List<Pelicula> buscarPeliculasPorPais(String pais);

    List<Pelicula> buscarPeliculasPorDirector(String director);

    List<Pelicula> buscarPeliculasPorGenero(String genero);

    List<Pelicula> buscarPeliculasPorImagenPortada(String imagen_portada);

    List<Actor> buscarActoresPorPelicula(Integer idPelicula);

    void guardarPeliculaConActores(Pelicula pelicula, List<Integer> idsActores);

    void guardarPelicula(Pelicula pelicula);

    void eliminarPelicula(Integer idPelicula);

    void actualizarPelicula(Pelicula pelicula);

}
