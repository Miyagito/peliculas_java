package es.uah.actoresPeliculas.dao;
import es.uah.actoresPeliculas.model.Actor;

import java.time.LocalDate;
import java.util.List;
public interface IActoresDAO {
    List<Actor> buscarTodos();
    Actor buscarActorPorId(Integer idActor);
    Actor buscarActorPorNombre(String nombre);
    List<Actor> buscarActoresPorFechaDeNacimiento(LocalDate fecha_nacimiento);
    List<Actor> buscarActoresPorPaisDeNacimiento(String pais_nacimiento);
    void guardarActor(Actor actor);
    void eliminarActor(Integer idActor);
    void actualizarActor(Actor actor);

}
