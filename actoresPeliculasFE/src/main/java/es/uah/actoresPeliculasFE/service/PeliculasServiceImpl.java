package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.controller.PeliculasController;
import es.uah.actoresPeliculasFE.model.Actor;
import es.uah.actoresPeliculasFE.model.Pelicula;
import es.uah.actoresPeliculasFE.model.PeliculaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PeliculasServiceImpl implements IPeliculasService {

    private static final Logger logger = LoggerFactory.getLogger(PeliculasController.class);

    @Autowired
    RestTemplate template;

    @Autowired
    private IActoresService actoresService;

    String api = "http://localhost:8001/api/peliculas";

    @Override
    public Page<Pelicula> buscarPeliculas(String query, String tipo, Pageable pageable) {
        if (tipo == "Año") {
            tipo = "anno";
        }
        String url = api + "/buscar?" + "tipo=" + tipo + "&" + "query=" + query;
        Pelicula[] peliculas = template.getForObject(url, Pelicula[].class);
        List<Pelicula> peliculasList = Arrays.asList(peliculas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pelicula> list;
        if (peliculasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, peliculasList.size());
            list = peliculasList.subList(startItem, toIndex);
        }
        Page<Pelicula> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), peliculasList.size());
        return page;
    }

    @Override
    public Page<Pelicula> buscarTodos(Pageable pageable) {
        Pelicula[] peliculas = template.getForObject(api, Pelicula[].class);
        List<Pelicula> peliculasList = Arrays.asList(peliculas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pelicula> list;

        if (peliculasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, peliculasList.size());
            list = peliculasList.subList(startItem, toIndex);
        }
        Page<Pelicula> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), peliculasList.size());
        return page;
    }

    @Override
    public List<Actor> buscarActoresEnPelicula(Integer id){
        Actor[] actores = template.getForObject(api + "/" + id + "/" + "actores", Actor[].class );
        return Arrays.asList(actores);
    }

    @Override
    public List<Actor> buscarActoresPorIds(List<Integer> ids) {
        return actoresService.buscarActoresPorIds(ids);
    }

    @Override
    public Pelicula buscarPeliculaPorId(Integer id) {
        Pelicula pelicula =  template.getForObject(api + "/" + id, Pelicula.class);
        return pelicula;
    }

    @Override
    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
        Pelicula[] peliculas = template.getForObject(api + "/titulo/" + titulo, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorDirector(String director) {
        Pelicula[] peliculas = template.getForObject(api + "/director/" + director, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorGenero(String genero) {
        Pelicula[] peliculas = template.getForObject(api + "/genero/" + genero, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public List<Pelicula> buscarPeliculasPorActor(String actor) {
        Pelicula[] peliculas = template.getForObject(api + "/actor/" + actor, Pelicula[].class);
        return Arrays.asList(peliculas);
    }

    @Override
    public void guardarPelicula(PeliculaRequest pelicula) {
        template.postForObject(api, pelicula, String.class);
    }

    @Override
    public void eliminarPelicula(Integer id) {
        template.delete(api + "/" + id);
    }

    @Override
    public void actualizarPelicula(PeliculaRequest pelicula) {
        template.put(api, pelicula, String.class);
    }

    // Puedes agregar más métodos según tus necesidades
}
