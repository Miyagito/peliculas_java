package es.uah.actoresPeliculasFE.service;

import es.uah.actoresPeliculasFE.model.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginServiceImpl {

    @Autowired
    private RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8002/api/users/validate";

    public UserCredentials buscarUsuarioPorCredenciales(String correo, String password) {
        UserCredentials credentials = new UserCredentials();
        credentials.setCorreo(correo);
        credentials.setPassword(password);

        // Enviar solicitud POST al backend y recibir la respuesta
        UserCredentials response = restTemplate.postForObject(apiUrl, credentials, UserCredentials.class);
        return response;
    }
}
