package es.uah.actoresPeliculas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table (name = "Peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "`anno`")
    private Integer anno;

    @Column(name = "duracion", nullable = false)
    private Integer duracion;

    @Column(name = "pais", nullable = false)
    private String pais;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Lob
    @Column(name = "sinopsis", nullable = false)
    private String sinopsis;

    @Column(name = "imagen_portada")
    private String imagenPortada;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Reparto",
            joinColumns = @JoinColumn(name = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_actor")
    )
    @JsonIgnoreProperties("peliculas")
    private Set<Actor> actores = new LinkedHashSet<>();
    public Pelicula(){}
    public Pelicula(Integer id, String titulo, Integer anno, Integer duracion, String pais, String director, String genero, String sinopsis, String imagenPortada) {
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

    public String getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public Set<Actor> getActores() {
        return actores;
    }

    public void setActores(Set<Actor> actores) {
        this.actores = actores;
    }

    public void agregarActor(Actor actor) {
        this.actores.add(actor);
        actor.getPeliculas().add(this);
    }

}