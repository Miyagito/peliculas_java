package es.uah.actoresPeliculas.model;

public class ActorDTO {
    private String nombre;
    private Integer id;

    public ActorDTO(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
