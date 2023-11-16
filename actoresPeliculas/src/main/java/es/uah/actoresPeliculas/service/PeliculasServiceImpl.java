package es.uah.actoresPeliculas.service;
import ch.qos.logback.core.BasicStatusManager;
import es.uah.actoresPeliculas.dao.IActoresDAO;
import es.uah.actoresPeliculas.dao.IPeliculasDAO;
import es.uah.actoresPeliculas.model.Actor;
import es.uah.actoresPeliculas.model.Pelicula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PeliculasServiceImpl implements IPeliculasService{
    @Autowired
    IPeliculasDAO peliculasDAO;

    @Autowired
    IActoresDAO actoresDAO;

    @Override
    public List<Pelicula> buscarTodos() {
        return peliculasDAO.buscarTodos();
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer idPelicula) {
        return peliculasDAO.buscarPeliculaPorId(idPelicula);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        return peliculasDAO.buscarPeliculasPorTitulo(titulo);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorAnno(Integer anno) {
        return peliculasDAO.buscarPeliculasPorAnno(anno);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorDuracion(Integer duracion) {
        return peliculasDAO.buscarPeliculasPorDuracion(duracion);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorPais(String pais) {

        return peliculasDAO.buscarPeliculasPorPais(pais);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorDirector(String director) {
        return peliculasDAO.buscarPeliculasPorDirector(director);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        return peliculasDAO.buscarPeliculasPorGenero(genero);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorImagenPortada(String imagen_portada) {
        return peliculasDAO.buscarPeliculasPorImagenPortada(imagen_portada);
    }

    @Override
    public List<Actor> buscarActoresPorPelicula(Integer idPelicula) {
        Pelicula pelicula = peliculasDAO.buscarPeliculaPorId(idPelicula);
        if (pelicula != null) {
            return new ArrayList<>(pelicula.getActores());
        }
        return Collections.emptyList();
    }

    @Override
    public void guardarPelicula(Pelicula pelicula) {
        for (Actor actor : pelicula.getActores()) {
            actor.agregarPelicula(pelicula);
        }
        peliculasDAO.guardarPelicula(pelicula);
    }

    // no lo estoy usando
    @Override
    public void guardarPeliculaConActores(Pelicula pelicula, List<Integer> idsActores) {
        for (Integer idActor : idsActores) {
            Actor actor = actoresDAO.buscarActorPorId(idActor);
            if (actor != null) {
                pelicula.agregarActor(actor);
            }
        }
        peliculasDAO.guardarPelicula(pelicula);
    }
    @Override
    public void eliminarPelicula(Integer idPelicula) {
        if (peliculasDAO.buscarPeliculaPorId(idPelicula)!=null) {
            peliculasDAO.eliminarPelicula(idPelicula);
        }
    }

    @Override
    public void actualizarPelicula(Pelicula pelicula) {
        if (peliculasDAO.buscarPeliculaPorId(pelicula.getId()) != null) {
            peliculasDAO.actualizarPelicula(pelicula);
        }
    }

}
