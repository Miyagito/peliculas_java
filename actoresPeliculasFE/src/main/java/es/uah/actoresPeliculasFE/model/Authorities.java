package es.uah.actoresPeliculasFE.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Authorities", schema = "usuariosdbsecurity")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol", nullable = false)
    private Integer idRol;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    // Getters y setters
    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authorities)) return false;
        Authorities that = (Authorities) o;
        return Objects.equals(idRol, that.idRol) && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, authority);
    }
}
