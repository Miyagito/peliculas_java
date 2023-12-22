package es.uah.actoresPeliculasFE.controller;

import es.uah.actoresPeliculasFE.model.Criticas;
import es.uah.actoresPeliculasFE.model.Pelicula;
import es.uah.actoresPeliculasFE.paginator.PageRender;
import es.uah.actoresPeliculasFE.service.ICriticasService;
import es.uah.actoresPeliculasFE.service.IPeliculasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/criticas")
public class CriticasController {

    @Autowired
    private ICriticasService criticasService;

    @Autowired
    private IPeliculasService peliculasService;

    @GetMapping("/busqueda")
    public String search(Model model) {
        return "criticas/search";
    }
    @GetMapping("/listado")
    public String listadoCriticas(Model model,
                                  @RequestParam(name = "query", required = false) String query,
                                  @RequestParam(name = "tipo", required = false) String tipo,
                                  @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Criticas> listado = criticasService.buscarCriticas(query, tipo, pageable);
        Map<Integer, String> peliculasNombres = new HashMap<>();

        listado.forEach(critica -> {
            Pelicula pelicula = peliculasService.buscarPeliculaPorId(critica.getIdPelicula());
            if (pelicula != null) {
                peliculasNombres.put(critica.getIdPelicula(), pelicula.getTitulo());
            }
        });
        PageRender<Criticas> pageRender = new PageRender<Criticas>("/criticas/listado", listado);
        model.addAttribute("titulo", "Listado de Críticas");
        model.addAttribute("listadoCriticas", listado);
        model.addAttribute("peliculasNombres", peliculasNombres);
        model.addAttribute("page", pageRender);
        return "criticas/listaCriticas";
    }


    @GetMapping("/borrar/{id}")
    public String eliminarUsuario(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Criticas critica = criticasService.buscarCriticaPorId(id);
        if (critica != null) {
            criticasService.eliminarCritica(id);
            attributes.addFlashAttribute("msg", "Los datos de la critica fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Critica no encontrado!");
        }

        return "redirect:/criticas/listado";
    }
    // Aquí añadirías los métodos para añadir, editar, borrar críticas, etc.
}

