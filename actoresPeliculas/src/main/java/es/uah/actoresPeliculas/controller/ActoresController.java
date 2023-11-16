package es.uah.actoresPeliculas.controller;
import es.uah.actoresPeliculas.model.Actor;
import es.uah.actoresPeliculas.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/actores")
public class ActoresController {

    @Autowired
    IActoresService actoresService;

    @GetMapping
    public List<Actor> buscarTodos() {
        return actoresService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Actor buscarActorPorId(@PathVariable("id") Integer id) {
        return actoresService.buscarActorPorId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Actor buscarActorPorNombre(@PathVariable("nombre") String nombre) {
        return actoresService.buscarActorPorNombre(nombre);
    }

    @GetMapping("/fecha_nacimiento/{fecha_nacimiento}")
    public List<Actor> buscarActoresPorFechaDeNacimiento(@PathVariable("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha_nacimiento) {
        return actoresService.buscarActoresPorFechaDeNacimiento(fecha_nacimiento);
    }

    @GetMapping("/pais/{pais}")
    public  List<Actor> buscarActoresPorPaisDeNacimiento(@PathVariable("pais") String pais) {
        return actoresService.buscarActoresPorPaisDeNacimiento(pais);
    }

    @PostMapping
    public void guardarActor(@RequestBody Actor actor) {
        actoresService.guardarActor(actor);
    }
    @PutMapping
    public void actualizarActor(@RequestBody Actor actor) {
        actoresService.actualizarActor(actor);
    }
    @DeleteMapping("/{id}")
    public void eliminarActor(@PathVariable("id") Integer id) {
        actoresService.eliminarActor(id);
    }
}
