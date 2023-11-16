package es.uah.actoresPeliculasFE.model;

import java.util.List;

public class PeliculaRequest {

    private Pelicula pelicula;

    private List<Integer> ids;

    public Pelicula getPelicula() {
        return pelicula;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
