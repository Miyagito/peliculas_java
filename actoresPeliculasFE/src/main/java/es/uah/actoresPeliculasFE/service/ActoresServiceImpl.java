package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ActoresServiceImpl implements IActoresService {

    @Autowired
    RestTemplate template;

    String url = "http://localhost:8001/api/actores";

    @Override
    public Page<Actor> buscarTodos(Pageable pageable) {
        Actor[] actores = template.getForObject(url, Actor[].class);
        List<Actor> actoresList = Arrays.asList(actores);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Actor> list;

        if(actoresList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, actoresList.size());
            list = actoresList.subList(startItem, toIndex);
        }
        Page<Actor> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), actoresList.size());
        return page;
    }

    @Override
    public List<Actor> buscarTodos() {
        Actor[] actores = template.getForObject(url, Actor[].class);
        return Arrays.asList(actores);
    }

    @Override
    public Actor buscarActorPorId(Integer id) {
        return template.getForObject(url + "/" + id, Actor.class);
    }

    @Override
    public Actor buscarActorPorNombre(String nombre) {
        return template.getForObject(url + "/nombre/" + nombre, Actor.class);
    }

    @Override
    public List<Actor> buscarActoresPorFechaDeNacimiento(LocalDate fechaNacimiento) {
        Actor[] actores = template.getForObject(url + "/fechaNacimiento/" + fechaNacimiento, Actor[].class);
        return Arrays.asList(actores);
    }

    @Override
    public List<Actor> buscarActoresPorPaisDeNacimiento(String paisNacimiento) {
        Actor[] actores = template.getForObject(url + "/paisNacimiento/" + paisNacimiento, Actor[].class);
        return Arrays.asList(actores);
    }

    @Override
    public List<Actor> buscarActoresPorIds(List<Integer> ids) {
        return null;
    }

    @Override
    public void guardarActor(Actor actor) {
        if (actor.getId() != null && actor.getId() > 0) {
            template.put(url, actor);
        } else {
            actor.setId(0);
            template.postForObject(url, actor, String.class);
        }
    }

    @Override
    public void eliminarActor(Integer id) {
        template.delete(url + "/" + id);
    }

    @Override
    public void actualizarActor(Actor actor) {
        template.put(url, actor);
    }
}

