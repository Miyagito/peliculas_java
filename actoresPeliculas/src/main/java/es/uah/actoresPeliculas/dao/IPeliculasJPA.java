package es.uah.actoresPeliculas.dao;
import es.uah.actoresPeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface IPeliculasJPA extends JpaRepository<Pelicula, Integer> {
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
    List<Pelicula> findByAnno(Integer anno);
    List<Pelicula> findByDuracion(Integer duracion);
    List<Pelicula> findByPaisContainingIgnoreCase(String pais);
    List<Pelicula> findByDirectorContainingIgnoreCase(String director);
    List<Pelicula> findByGeneroContainingIgnoreCase(String genero);
    List<Pelicula> findByImagenPortada(String imagen_portada);
}
