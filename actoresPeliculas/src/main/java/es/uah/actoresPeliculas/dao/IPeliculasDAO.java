package es.uah.actoresPeliculas.dao;
import es.uah.actoresPeliculas.model.Pelicula;
import java.util.List;
public interface IPeliculasDAO {
    List<Pelicula> buscarTodos();
    Pelicula buscarPeliculaPorId(Integer idPelicula);
    List<Pelicula> buscarPeliculasPorTitulo(String titulo);
    List<Pelicula> buscarPeliculasPorAnno(Integer anno);
    List<Pelicula> buscarPeliculasPorDuracion(Integer duracion);
    List<Pelicula> buscarPeliculasPorPais(String pais);
    List<Pelicula> buscarPeliculasPorDirector(String director);
    List<Pelicula> buscarPeliculasPorGenero(String genero);
    List<Pelicula> buscarPeliculasPorImagenPortada(String imagen_portada);
    void guardarPelicula(Pelicula pelicula);
    void eliminarPelicula(Integer idPelicula);
    void actualizarPelicula(Pelicula pelicula);

}
