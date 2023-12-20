package es.uah.actoresPeliculasFE.model;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Criticas", schema = "usuariosdbsecurity")
public class Criticas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCriticas", nullable = false)
    private Integer idCriticas;

    @Column(name = "idPelicula", nullable = false)
    private Integer idPelicula;

    @Column(name = "nota", nullable = false)
    private Integer nota;

    @Column(name = "valoracion", nullable = false, length = 1024)
    private String valoracion;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    private Usuario user;

    // Getters y setters
    public Integer getIdCriticas() {
        return idCriticas;
    }

    public void setIdCriticas(Integer idCriticas) {
        this.idCriticas = idCriticas;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Criticas criticas = (Criticas) o;
        return Objects.equals(idCriticas, criticas.idCriticas);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
