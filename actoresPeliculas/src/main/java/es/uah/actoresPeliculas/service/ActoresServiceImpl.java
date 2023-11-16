package es.uah.actoresPeliculas.service;
import es.uah.actoresPeliculas.dao.IActoresDAO;
import es.uah.actoresPeliculas.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ActoresServiceImpl implements IActoresService{

    @Autowired
    IActoresDAO actoresDAO;

    @Override
    public List<Actor> buscarTodos() {
        return actoresDAO.buscarTodos();
    }

    @Override
    public Actor buscarActorPorId(Integer idActor) {
        return actoresDAO.buscarActorPorId(idActor);
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        return actoresDAO.buscarActorPorNombre(nombre);
    }

    @Override
    public List<Actor> buscarActoresPorFechaDeNacimiento(LocalDate fecha_nacimiento) {
        return actoresDAO.buscarActoresPorFechaDeNacimiento(fecha_nacimiento);
    }

    @Override
    public List<Actor> buscarActoresPorPaisDeNacimiento(String pais_nacimiento) {
        return actoresDAO.buscarActoresPorPaisDeNacimiento(pais_nacimiento);
    }

    @Override
    public void guardarActor(Actor actor) {
        if(actoresDAO.buscarActorPorId(actor.getId())==null){
            actoresDAO.guardarActor(actor);
        }
    }

    @Override
    public void eliminarActor(Integer idActor) {
        if (actoresDAO.buscarActorPorId(idActor)!=null) {
            actoresDAO.eliminarActor(idActor);
        }
    }

    @Override
    public void actualizarActor(Actor actor) {
        if (actoresDAO.buscarActorPorId(actor.getId())!=null) {
            actoresDAO.actualizarActor(actor);
        }
    }
}
