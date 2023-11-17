package es.uah.actoresPeliculasFE.controller;

import es.uah.actoresPeliculasFE.model.Actor;
import es.uah.actoresPeliculasFE.model.Pelicula;
import es.uah.actoresPeliculasFE.service.IActoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import es.uah.actoresPeliculasFE.paginator.PageRender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/actores")
public class ActoresController {

    @Autowired
    IActoresService actoresService;

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/listado")
    public String listadoActores(Model model, @RequestParam(name="page", defaultValue="0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Actor> listado = actoresService.buscarTodos(pageable);
        PageRender<Actor> pageRender = new PageRender<Actor>("/actores/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoActores", listado);
        model.addAttribute("page", pageRender);
        return "actores/listaActores";
    }
    @GetMapping("/nuevo")
    public String nuevoActor(Model model) {
        model.addAttribute("titulo", "Nuevo actor");
        model.addAttribute("actor", new Actor());
        return "actores/formActor";
    }

    @PostMapping("/guardar/")
    public String guardarActor(Model model, Actor actor, RedirectAttributes attributes) {
        actoresService.guardarActor(actor);
        model.addAttribute("titulo", "Nuevo actor");
        attributes.addFlashAttribute("msg", "Los datos del actor fueron guardados!");
        return "redirect:/actores/listado";
    }

    @GetMapping("/editar/{id}")
    public String editarActor(Model model, @PathVariable("id") Integer id) {
        Actor actor = actoresService.buscarActorPorId(id);
        model.addAttribute("titulo", "Editar actor");
        model.addAttribute("actor", actor);
        return "actores/formActor";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarActor(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Actor actor = actoresService.buscarActorPorId(id);
        if (actor != null) {
            actoresService.eliminarActor(id);
            attributes.addFlashAttribute("msg", "Los datos del actor fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Actor no encontrado!");
        }

        return "redirect:/actores/listado";
    }

    @GetMapping("/buscarPorFecha")
    public String buscarActoresPorFecha(@RequestParam("fecha") String fecha, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);
        List<Actor> listado = actoresService.buscarActoresPorFechaDeNacimiento(fechaNacimiento);
        model.addAttribute("titulo", "Listado de actores por fecha de nacimiento");
        model.addAttribute("listadoActores", listado);
        return "actores/listActor";
    }

    @GetMapping("/buscarPorNombre")
    public String buscarActoresPorNombre(@RequestParam("nombre") String nombre, Model model) {
        Actor actor = actoresService.buscarActorPorNombre(nombre);
        model.addAttribute("titulo", "Listado de actores por nombre");
        model.addAttribute("listadoActores", List.of(actor));
        return "actores/listActor";
    }

    @GetMapping("/buscarPorPais")
    public String buscarActoresPorPais(@RequestParam("pais") String pais, Model model) {
        List<Actor> listado = actoresService.buscarActoresPorPaisDeNacimiento(pais);
        model.addAttribute("titulo", "Listado de actores por país de nacimiento");
        model.addAttribute("listadoActores", listado);
        return "actores/listActor";
    }
}

