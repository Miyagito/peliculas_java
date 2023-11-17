package es.uah.actoresPeliculasFE.model;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PeliculaRequest {
    private Pelicula pelicula;

    private List<Integer> ids;

    private Integer id;

    public Pelicula getPelicula() {
        return pelicula;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public Integer getId() {return id;}

    public void  setId (Integer id) {this.id = id;}
    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

}
