package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthoritiesServiceImpl implements IAuthoritiesService {
    @Autowired
    RestTemplate restTemplate;

    String url = "http://localhost:8002/api/authorities";

    @Override
    public List<Authorities> buscarTodos() {

        Authorities[] authoritiesArray = restTemplate.getForObject(url, Authorities[].class);
        return Arrays.asList(authoritiesArray);
    }
}
