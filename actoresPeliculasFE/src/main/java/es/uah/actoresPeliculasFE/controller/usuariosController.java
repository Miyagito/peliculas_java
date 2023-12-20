package es.uah.actoresPeliculasFE.controller;

import es.uah.actoresPeliculasFE.model.Authorities;
import es.uah.actoresPeliculasFE.model.Usuario;
import es.uah.actoresPeliculasFE.paginator.PageRender;
import es.uah.actoresPeliculasFE.service.IAuthoritiesService;
import es.uah.actoresPeliculasFE.service.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class usuariosController {
@Autowired
IUsuariosService usuariosService;

@Autowired
IAuthoritiesService authoritiesService;

    @GetMapping("/listado")
    public String listadoUsuarios(Model model, @RequestParam(name = "query", required = false) String query, @RequestParam(name = "tipo", required = false) String tipo, @RequestParam(name = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Usuario> listado = usuariosService.buscarTodos(query, tipo, pageable);
        PageRender<Usuario> pageRender = new PageRender<Usuario>("/usuarios/listado", listado);
        model.addAttribute("titulo", "Listado de todos los actores");
        model.addAttribute("listadoUsuarios", listado);
        model.addAttribute("page", pageRender);
        return "usuarios/listaUsuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuario(Model model) {
        List<Authorities> allRoles = authoritiesService.buscarTodos();
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("allRoles",allRoles );
        return "usuarios/formUsuario";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(Model model, @PathVariable("id") Integer id) {
        Usuario usuario = usuariosService.buscarUsuarioPorId(id);
        List<Authorities> allRoles = authoritiesService.buscarTodos();
        if (usuario != null) {
            model.addAttribute("titulo", "Editar Usuario");
            model.addAttribute("usuario", usuario);
            model.addAttribute("allRoles",allRoles );
            return "usuarios/formUsuario";
        } else {
            return "redirect:/usuarios/listado";
        }
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                 @RequestParam("idRol") Integer idRol,
                                 @RequestParam("authority") String authorityValue,
                                 RedirectAttributes attributes) {
        Authorities authority = new Authorities();
        authority.setIdRol(idRol);
        authority.setAuthority(authorityValue);

        List<Authorities> authoritiesList = new ArrayList<>();
        authoritiesList.add(authority);

        usuario.setAuthorities(authoritiesList);

        usuariosService.guardarOEditarUsuario(usuario);

        String mensaje = (usuario.getIdUsuario() != null)
                ? "Los datos del usuario fueron actualizados!"
                : "El usuario fue creado con éxito!";
        attributes.addFlashAttribute("msg", mensaje);
        return "redirect:/usuarios/listado";
    }

    @GetMapping("/borrar/{id}")
    public String eliminarUsuario(Model model, @PathVariable("id") Integer id, RedirectAttributes attributes) {
        Usuario usuario = usuariosService.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuariosService.eliminarUsuario(id);
            attributes.addFlashAttribute("msg", "Los datos del usuario fueron borrados!");
        } else {
            attributes.addFlashAttribute("msg", "Usuario no encontrado!");
        }

        return "redirect:/usuarios/listado";
    }

    @GetMapping("/busqueda")
    public String search(Model model) {
        return "usuarios/search";
    }

}
