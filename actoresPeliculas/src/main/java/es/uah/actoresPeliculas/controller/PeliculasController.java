package es.uah.actoresPeliculas.controller;

import es.uah.actoresPeliculas.model.Actor;
import es.uah.actoresPeliculas.model.ActorDTO;
import es.uah.actoresPeliculas.model.Pelicula;
import es.uah.actoresPeliculas.model.PeliculaDTO;
import es.uah.actoresPeliculas.service.IActoresService;
import es.uah.actoresPeliculas.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasController {

    @Autowired
    private IPeliculasService peliculasService;

    @Autowired
    private IActoresService actoresService;

    // Métodos para gestionar películas

    @GetMapping
    public List<Pelicula> buscarTodos() {
        return peliculasService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Pelicula buscarPeliculaPorId(@PathVariable("id") Integer id) {
        return peliculasService.buscarPeliculaPorId(id);
    }

    @GetMapping("/buscar")
    public List<Pelicula> buscarPeliculasPorParametros(@RequestParam(name = "tipo", defaultValue = "") String tipo, @RequestParam("query") String query) {
        switch (tipo.toLowerCase()) {
            case "titulo":
                return peliculasService.buscarPeliculasPorTitulo(query);
            case "director":
                return peliculasService.buscarPeliculasPorDirector(query);
            case "genero":
                return peliculasService.buscarPeliculasPorGenero(query);
            case "año":
                return peliculasService.buscarPeliculasPorAnno(Integer.parseInt(query));
            case "todos":
                return peliculasService.buscarTodos();
            // Agrega más casos según los tipos de búsqueda que admitas
            default:
                return peliculasService.buscarTodos();
        }
    }

    @GetMapping("/titulo/{titulo}")
    public List<Pelicula> buscarPeliculasPorTitulo(@PathVariable("titulo") String titulo) {
        return peliculasService.buscarPeliculasPorTitulo(titulo);
    }

    @GetMapping("/año/{anno}")
    public List<Pelicula> buscarPeliculasPorAnno(@PathVariable("anno") Integer anno) {
        return peliculasService.buscarPeliculasPorAnno(anno);
    }

    @GetMapping("/duracion/{duracion}")
    public List<Pelicula> buscarPeliculasPorDuracion(@PathVariable("duracion") Integer duracion) {
        return peliculasService.buscarPeliculasPorDuracion(duracion);
    }

    @GetMapping("/pais/{pais}")
    public List<Pelicula> buscarPeliculasPorPais(@PathVariable("pais") String pais) {
        return peliculasService.buscarPeliculasPorPais(pais);
    }

    @GetMapping("/director/{director}")
    public List<Pelicula> buscarPeliculasPorDirector(@PathVariable("director") String director) {
        return peliculasService.buscarPeliculasPorDirector(director);
    }

    @GetMapping("/genero/{genero}")
    public List<Pelicula> buscarPeliculasPorGenero(@PathVariable("genero") String genero) {
        return peliculasService.buscarPeliculasPorGenero(genero);
    }

    @GetMapping("/imagen/{imagen}")
    public List<Pelicula> buscarPeliculasPorImagenPortada(@PathVariable("imagen") String imagen) {
        return peliculasService.buscarPeliculasPorImagenPortada(imagen);
    }

    @GetMapping("/{id}/actores")
    public ResponseEntity<List<ActorDTO>> obtenerActoresDePelicula(@PathVariable Integer id) {
        List<Actor> actores = peliculasService.buscarActoresPorPelicula(id);
        List<ActorDTO> actoresDTO;

        if (actores.isEmpty()) {
            // Si no hay actores, devolver un ActorDTO con nombre e ID vacíos
            actoresDTO = Collections.singletonList(new ActorDTO("", null));
        } else {
            actoresDTO = actores.stream()
                    .map(actor -> new ActorDTO(actor.getNombre(), actor.getId()))
                    .collect(Collectors.toList());
        }
        return new ResponseEntity<>(actoresDTO, HttpStatus.OK);
    }

    @PostMapping
    public void guardarPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        Pelicula pelicula = peliculaDTO.getPelicula();
        List<Integer>  ids = peliculaDTO.getActores();
        if (ids != null) {
            List<Actor> actores = new ArrayList<>();
            for (Integer id : ids) {
                Actor actor = actoresService.buscarActorPorId(id);
                if (actor != null) {
                    actores.add(actor);
                } else {
                    actores.add(new Actor());
                }
            }
            pelicula.setActores(new HashSet<>(actores));
        }
        peliculasService.guardarPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public void actualizarPelicula(@PathVariable("id") Integer id, @RequestBody PeliculaDTO peliculaDTO) {
        // Obtén la película existente por ID
        Pelicula peliculaExistente = peliculasService.buscarPeliculaPorId(id);

        if (peliculaExistente != null) {
            // Actualiza los campos de la película con los valores del DTO
            peliculaExistente.setTitulo(peliculaDTO.getPelicula().getTitulo());
            peliculaExistente.setDirector(peliculaDTO.getPelicula().getDirector());
            // Actualiza otros campos según sea necesario

            // Actualiza la lista de actores
            List<Integer> idsActores = peliculaDTO.getActores();
            if (idsActores != null) {
                List<Actor> actores = new ArrayList<>();
                for (Integer actorId : idsActores) {
                    Actor actor = actoresService.buscarActorPorId(actorId);
                    if (actor != null) {
                        actores.add(actor);
                    } else {
                        actores.add(new Actor());
                    }
                }
                peliculaExistente.setActores(new HashSet<>(actores));
            } else {
                // Si la lista de actores no se proporciona en la solicitud, puedes optar por borrarla o dejarla intacta,
                // dependiendo de tus requisitos.
                peliculaExistente.setActores(Collections.emptySet()); // Para borrar la lista
            }

            // Guarda la película actualizada
            peliculasService.actualizarPelicula(peliculaExistente);
        } else {
            // Manejar el caso en que la película no existe
            // Puedes devolver un error o realizar alguna otra acción
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPelicula(@PathVariable("id") Integer id) {
        Pelicula peliculaExistente = peliculasService.buscarPeliculaPorId(id);

        if (peliculaExistente != null) {
            peliculasService.eliminarPelicula(id);
            return new ResponseEntity<>("Pelicula eliminada correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No se encontró la película con ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

}


