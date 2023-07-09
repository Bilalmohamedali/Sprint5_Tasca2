package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "jugadores")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "jugador")
    private String nombre;
    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
