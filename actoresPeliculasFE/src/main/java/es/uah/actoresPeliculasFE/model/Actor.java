package es.uah.actoresPeliculasFE.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Actor {
    private Integer id;
    private String nombre;
    private String paisNacimiento;
    private LocalDate fechaNacimiento;
    private List<Pelicula> peliculas;

    public Actor(String nombre, String paisNacimiento, LocalDate fechaNacimiento){
        this.id = 0;
        this.nombre= nombre;
        this.fechaNacimiento= fechaNacimiento;
        this.paisNacimiento= paisNacimiento;
        this.peliculas = new ArrayList<>();

    }
    public Actor() {
    }
    public  Integer getId(){
        return id;
    }
    public void  setId(Integer id){
        this.id = id;
    }

    public  String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public  String getPaisNacimiento(){
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", paisNacimiento=" + paisNacimiento + '\'' +
                ", peliculas=" + peliculas +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(id, actor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}