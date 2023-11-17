package es.uah.actoresPeliculasFE.model;

import jakarta.persistence.Column;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pelicula {


    private Integer anno;
    private Integer id;
    private String titulo;
    private Integer duracion;
    private String pais;
    private String director;
    private String genero;

    private String sinopsis;

    private String imagenPortada;

    public Pelicula(Integer id, String titulo, Integer anno, Integer duracion, String pais, String director, String genero,String sinopsis,String imagenPortada, List<Actor> reparto, List<Pelicula> peliculas, Set<Actor> actores) {
        this.id = id;
        this.titulo = titulo;
        this.anno = anno;
        this.duracion = duracion;
        this.pais = pais;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.imagenPortada = imagenPortada;

    }

    public Pelicula() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(Integer anno) {
        this.anno = anno;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pelicula)) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}

