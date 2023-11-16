package es.uah.actoresPeliculas.dao;

import es.uah.actoresPeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class IActoresDAOImp implements IActoresDAO {

    @Autowired
    IActoresJPA actoresJPA;
    @Autowired
    IPeliculasJPA peliculasJPA;

    @Override
    public List<Actor> buscarTodos() {
        return actoresJPA.findAll();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        Optional<Actor> optional = actoresJPA.findById(idActor);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        Optional<Actor> optional =
                Optional.ofNullable(actoresJPA.findByNombre(nombre));
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Actor> buscarActoresPorFechaDeNacimiento(LocalDate fecha_nacimiento) {
        return actoresJPA.findByFechaNacimiento(fecha_nacimiento);
    }

    @Override
    public List<Actor> buscarActoresPorPaisDeNacimiento(String pais_nacimiento) {
        return actoresJPA.findByPaisNacimiento(pais_nacimiento);
    }

    @Override
    public void guardarActor(Actor actor) {
        actoresJPA.save(actor);
    }

    @Override
    public void eliminarActor(Integer idActor) {
        actoresJPA.deleteById(idActor);
    }

    @Override
    public void actualizarActor(Actor actor) {
        actoresJPA.save(actor);
    }
}
