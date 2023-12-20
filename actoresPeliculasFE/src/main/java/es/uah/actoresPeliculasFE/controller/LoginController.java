package es.uah.actoresPeliculasFE.controller;

import es.uah.actoresPeliculasFE.model.UserCredentials;
import es.uah.actoresPeliculasFE.service.LoginServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/admin-home")
    public String home() {
        return "admin-home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userCredentials", new UserCredentials());
        return "login";
    }

    @GetMapping("/rolSelection")
    public String rolSelection(Model model) {
        return "rolSelection";
    }

    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute UserCredentials userCredentials, HttpServletResponse response) {
        UserCredentials authenticatedUser = loginService.buscarUsuarioPorCredenciales(
                userCredentials.getCorreo(), userCredentials.getPassword());

        if (authenticatedUser != null) {
            try {
                // Crea una cookie con la información del usuario
                ObjectMapper objectMapper = new ObjectMapper();
                String userInfoJson = objectMapper.writeValueAsString(authenticatedUser);
                String base64UserInfo = Base64.getEncoder().encodeToString(userInfoJson.getBytes());
                Cookie userInfoCookie = new Cookie("userInfo", base64UserInfo);
                userInfoCookie.setPath("/");
                userInfoCookie.setMaxAge(60 * 60); // Expira en 1 hora
                response.addCookie(userInfoCookie);

                // Redirecciona según los roles del usuario
                Integer rolId = authenticatedUser.getIdRoles();

                if (rolId != null) {
                    if (rolId == 1) {
                        // El usuario tiene el rol 1
                        return "redirect:/admin-home";
                    } else if (rolId == 2) {
                        // El usuario tiene el rol 2
                        return "redirect:/user-home";
                    }
                    // Agrega más condicionales según sea necesario para otros roles
                } else {
                    // El usuario no tiene ningún rol asignado
                    return "redirect:/login?error";
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace(); // Log the exception or handle it as you deem necessary
            }
        }
        // Si la autenticación falla o hay una excepción, redirige al login con un mensaje de error
        return "redirect:/login?error";
    }

}