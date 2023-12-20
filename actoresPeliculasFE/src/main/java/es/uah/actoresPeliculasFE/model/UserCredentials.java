package es.uah.actoresPeliculasFE.model;

import java.util.List;

public class UserCredentials {
    private String correo;
    private String password;
    private List<Integer> idRoles;
    // Getters y setters
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getIdRoles(){ return idRoles; }

    public void setIdRoles( List<Integer> idRoles ) { this.idRoles = idRoles; }
}

