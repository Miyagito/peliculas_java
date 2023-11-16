package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Actor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IActoresService {
    Page<Actor> buscarTodos(Pageable pageable);
    List<Actor> buscarTodos();
    Actor buscarActorPorId(Integer id);
    Actor buscarActorPorNombre(String nombre);
    List<Actor> buscarActoresPorFechaDeNacimiento(LocalDate fechaNacimiento);
    List<Actor> buscarActoresPorPaisDeNacimiento(String paisNacimiento);
    List<Actor> buscarActoresPorIds(List<Integer> ids);
    void guardarActor(Actor actor);
    void eliminarActor(Integer id);
    void actualizarActor(Actor actor);


}
