package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl {

    @Autowired
    private RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8002/api/users/validate";

    public UserCredentials buscarUsuarioPorCredenciales(String correo, String password) {
        Map<String, Object> credentialsMap = new HashMap<>();
        credentialsMap.put("correo", correo);
        credentialsMap.put("password", password);

        // Enviar solicitud POST al backend y recibir la respuesta
        UserCredentials response = restTemplate.postForObject(apiUrl, credentialsMap, UserCredentials.class);
        return response;
    }

}
