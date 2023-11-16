package es.uah.actoresPeliculas.dao;
import es.uah.actoresPeliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IActoresJPA extends JpaRepository<Actor, Integer> {
    Actor findByNombre(String nombre);
    List<Actor> findByFechaNacimiento(LocalDate fechaNacimiento);
    List<Actor> findByPaisNacimiento(String pais_nacimiento);
}
