package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document(collection = "players")
public class Jugador {
    @Id
    private String id;
    private String nombre;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;

    private String username;
    private String password;
    @JsonIgnore
    private List<Tirada> tiradas;

    public Jugador() {
    }

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.fechaRegistro = LocalDate.now();
    }

    public Jugador(String nombre, LocalDate fechaRegistro) {
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Tirada> getTiradas() {
        return tiradas;
    }

    public void setTiradas(List<Tirada> tiradas) {
        this.tiradas = tiradas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id) && Objects.equals(nombre,jugador.nombre) && Objects.equals(fechaRegistro, jugador.fechaRegistro) && Objects.equals(tiradas, jugador.tiradas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre,fechaRegistro,tiradas);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", tiradas=" + tiradas +
                '}';
    }
}
