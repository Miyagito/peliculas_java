package es.uah.actoresPeliculas.dao;
import es.uah.actoresPeliculas.model.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public class IPeliculasDAOImp implements IPeliculasDAO {
    @Autowired
    IPeliculasJPA peliculasJPA;

    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasJPA.findAll();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPeliculas) {
        Optional<Pelicula> optional = peliculasJPA.findById(idPeliculas);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        return peliculasJPA.findByTituloContainingIgnoreCase(titulo);
    }
    @Override
    public List<Pelicula> buscarPeliculasPorAnno(Integer anno) {
        return peliculasJPA.findByAnno(anno);
    }
    @Override
    public List<Pelicula> buscarPeliculasPorDuracion(Integer duracion) {
        return peliculasJPA.findByDuracion(duracion);
    }
    @Override
    public List<Pelicula> buscarPeliculasPorPais(String pais) {
        return peliculasJPA.findByPaisContainingIgnoreCase(pais);
    }
    @Override
    public List<Pelicula> buscarPeliculasPorDirector(String director) {
        return peliculasJPA.findByDirectorContainingIgnoreCase(director);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        return peliculasJPA.findByGeneroContainingIgnoreCase(genero);
    }
    @Override
    public List<Pelicula> buscarPeliculasPorImagenPortada(String imagen_portada) {
        return peliculasJPA.findByImagenPortada(imagen_portada);
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }

    @Override
    public void eliminarPelicula(Integer idPelicula) {
        peliculasJPA.deleteById(idPelicula);
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        peliculasJPA.save(pelicula);
    }

}
