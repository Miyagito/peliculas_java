package es.uah.actoresPeliculasFE.model;

import java.util.Objects;

public class Reparto {

    private Integer idReparto;
    private Integer idActor;
    private Integer idPelicula;

    public Reparto() {
    }

    public Integer getIdReparto() {
        return idReparto;
    }

    public void setIdReparto(Integer idReparto) {
        this.idReparto = idReparto;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reparto)) return false;
        Reparto reparto = (Reparto) o;
        return Objects.equals(idReparto, reparto.idReparto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparto);
    }
}


