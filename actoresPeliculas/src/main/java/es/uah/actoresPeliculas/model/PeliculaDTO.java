package es.uah.actoresPeliculas.model;

import java.util.List;

public class PeliculaDTO {
    private Pelicula pelicula;
    private List<Integer> ids;

    private  Integer id;

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public Integer getId(){
        return id;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<Integer> getActores() {
        return ids;
    }

    public void setActores(List<Integer> ids) {
        this.ids = ids;
    }
}

