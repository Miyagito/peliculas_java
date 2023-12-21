package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuariosService {
    @Autowired
    RestTemplate template;

    String url = "http://localhost:8002/api/users";

    @Override
    public Page<Usuario> buscarUsuarios(String query, String tipo, Pageable pageable) {
        if (tipo == "Año") {
            tipo = "anno";
        }
        String api = url + "/buscar?" + "tipo=" + tipo + "&" + "query=" + query;
        Usuario[] usuarios = template.getForObject(api, Usuario[].class);
        List<Usuario> usuariosList = Arrays.asList(usuarios);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Usuario> list;
        if (usuariosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, usuariosList.size());
            list = usuariosList.subList(startItem, toIndex);
        }
        Page<Usuario> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), usuariosList.size());
        return page;
    }

    @Override
    public Page<Usuario> buscarTodos(String query, String tipo, Pageable pageable) {

        Usuario[] usuarios = template.getForObject(url, Usuario[].class);
        List<Usuario> usuariosList = Arrays.asList(usuarios);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Usuario> list;

        if(usuariosList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, usuariosList.size());
            list = usuariosList.subList(startItem, toIndex);
        }
        Page<Usuario> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), usuariosList.size());
        return page;
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer id) {
        String buscarUrl = url + "/" + id;
        try {
            return template.getForObject(buscarUrl, Usuario.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void guardarOEditarUsuario(Usuario usuario) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Usuario> request = new HttpEntity<>(usuario, headers);

        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
            // Este endpoint manejará tanto la actualización como la creación.
            template.put(url, request);
        } else {
            // Si prefieres puedes usar el método postForObject para mayor claridad.
            template.postForObject(url, request, Usuario.class);
        }
    }

    @Override
    public void eliminarUsuario(Integer id) {
        String deleteUrl = url + "/" + id;
        template.delete(deleteUrl);
    }
}

