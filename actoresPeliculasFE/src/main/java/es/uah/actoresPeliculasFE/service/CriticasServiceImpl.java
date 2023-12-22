package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Criticas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CriticasServiceImpl implements ICriticasService {

    @Autowired
    RestTemplate template;

    private final String url = "http://localhost:8002/api/criticas";

    @Override
    public Page<Criticas> buscarCriticas(String query, String tipo, Pageable pageable) {
        String api = url + "/buscar?" + "tipo=" + tipo + "&" + "query=" + query;
        Criticas[] criticas = template.getForObject(api, Criticas[].class);
        List<Criticas> criticasList = Arrays.asList(criticas);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Criticas> list;

        if(criticasList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, criticasList.size());
            list = criticasList.subList(startItem, toIndex);
        }
        Page<Criticas> page = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), criticasList.size());
        return page;
    }

    @Override
    public void eliminarCritica(Integer id) {
        String deleteUrl = url + "/" + id;
        template.delete(deleteUrl);
    }

    @Override
    public Criticas buscarCriticaPorId(Integer id) {
        String buscarUrl = url + "/" + id;
        try {
            return template.getForObject(buscarUrl, Criticas.class);
        } catch (Exception e) {
            return null;
        }
    }

    // Aquí añadirías los métodos para añadir, editar, borrar críticas, etc.
}

