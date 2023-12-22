package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Criticas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICriticasService {
    Page<Criticas> buscarCriticas(String query, String tipo, Pageable pageable);

    void eliminarCritica(Integer id);

    Criticas buscarCriticaPorId(Integer id);
}


