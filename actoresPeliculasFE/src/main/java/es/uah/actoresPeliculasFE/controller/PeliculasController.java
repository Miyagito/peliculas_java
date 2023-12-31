package es.uah.actoresPeliculasFE.controller;

import es.uah.actoresPeliculasFE.model.Actor;
import es.uah.actoresPeliculasFE.model.Pelicula;
import es.uah.actoresPeliculasFE.model.PeliculaRequest;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import es.uah.actoresPeliculasFE.service.IPeliculasService;
import es.uah.actoresPeliculasFE.service.IActoresService;
import es.uah.actoresPeliculasFE.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import es.uah.actoresPeliculasFE.paginator.PageRender;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

    private static final Logger logger = LoggerFactory.getLogger(PeliculasController.class);

    @Autowired
    private IPeliculasService peliculasService;

    @Autowired
    private IActoresService actoresService;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping("/busqueda")
    public String search(Model model) {
        return "peliculas/search";
    }

    @GetMapping(value = {"/listado"})
    public String listadoPeliculas(HttpServletRequest request, Model model, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "tipo", required = false) String tipo, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pelicula> listadoDePeliculas;

        listadoDePeliculas = peliculasService.buscarPeliculas(query, tipo, pageable);
        PageRender<Pelicula> pageRender = new PageRender<>("/peliculas/listado", listadoDePeliculas);
        model.addAttribute("titulo", "Listado de todas las películas");
        model.addAttribute("listadoPeliculas", listadoDePeliculas);
        model.addAttribute("page", pageRender);

        return "peliculas/listaPeliculas";
    }


    @GetMapping("/nueva")
    public String buscarPeliculasPorTitulo(Model model) {
        model.addAttribute("titulo", "Nueva película");
        model.addAttribute("actores", actoresService.buscarTodos());
        model.addAttribute("pelicula", new Pelicula());
        return "peliculas/formPelicula";
    }

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;

        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
                .body(recurso);
    }

    @GetMapping("/detalle/{id}")
    public String buscarPeliculaPorId(Model model, @PathVariable("id") Integer id) {
        List<Actor> listaDeActoresEnPelicula;
        listaDeActoresEnPelicula = peliculasService.buscarActoresEnPelicula(id);
        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        model.addAttribute("titulo", "Detalles de la película");
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("actores", listaDeActoresEnPelicula);
        return "peliculas/detallePelicula";
    }


    @PostMapping("/guardar")
    public String guardarPelicula(
            @RequestParam("titulo") String titulo,
            @RequestParam("anno") Integer anno,
            @RequestParam("duracion") Integer duracion,
            @RequestParam("pais") String pais,
            @RequestParam("director") String director,
            @RequestParam("genero") String genero,
            @RequestParam("sinopsis") String sinopsis,
            @RequestParam("imagenPortada") String imagenPortada,
            @RequestParam("actores") List<Integer> actoresIds,
            Model model, RedirectAttributes attributes) {

        // Crear una instancia de Pelicula y establecer sus propiedades
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelicula.setAnno(anno);
        pelicula.setDuracion(duracion);
        pelicula.setPais(pais);
        pelicula.setDirector(director);
        pelicula.setGenero(genero);
        pelicula.setSinopsis(sinopsis);
        pelicula.setImagenPortada(imagenPortada);
        // Establecer otras propiedades según sea necesario

        // Obtener la lista de actores a partir de los IDs
        // List<Actor> actores = actoresService.buscarActoresPorIds(actoresIds);


        //pelicula.setActores(new HashSet<>(actores));
        // Establecer la lista de actores en la película
        //pelicula.setActores((Set<Actor>) actores);
        PeliculaRequest peliculaRequest = new PeliculaRequest();
        peliculaRequest.setPelicula(pelicula);
        peliculaRequest.setIds(actoresIds);


        // Guardar la película
        peliculasService.guardarPelicula(peliculaRequest);
     /*   if(pelicula != null){
            peliculasService.guardarPelicula(pelicula);
        }*/

        model.addAttribute("titulo", "Nueva película");
        attributes.addFlashAttribute("msg", "Los datos de la película fueron guardados!");
        return "redirect:/peliculas/listado";
    }



    @GetMapping("/editar/{id}")
    public String editarPelicula(Model model, @PathVariable("id") Integer id) {
        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        List<Actor> actoresEnPelicula = peliculasService.buscarActoresEnPelicula(id);
        for (Actor actor : actoresEnPelicula) {
            System.out.println("Actor en la película: " + actor);
        }
        model.addAttribute("titulo", "Editar película");
        model.addAttribute("actores", actoresService.buscarTodos());
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("actoresEnPelicula", actoresEnPelicula); // Nueva línea

        return "peliculas/formPelicula";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarPelicula(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Pelicula pelicula = peliculasService.buscarPeliculaPorId(id);
        if (pelicula != null) {
            peliculasService.eliminarPelicula(id);
            attributes.addFlashAttribute("msg", "Los datos de la película fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Película no encontrada!");
        }

        return "redirect:/peliculas/listado";
    }

    // Otros métodos según las necesidades de tu aplicación

}
