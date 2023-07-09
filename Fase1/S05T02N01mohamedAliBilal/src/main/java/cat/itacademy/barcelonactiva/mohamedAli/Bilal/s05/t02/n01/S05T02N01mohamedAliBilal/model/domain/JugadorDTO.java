package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain;

import java.time.LocalDate;
import java.util.Objects;

public class JugadorDTO {

    private long id;
    private String nombre;
    private LocalDate fechaRegistro;
    private double porcentajeExito;

    public JugadorDTO(long id, String nombre, LocalDate fechaRegistro, double porcentajeExito) {
        this.id = id;
        this.nombre = nombre;
        this.fechaRegistro = fechaRegistro;
        this.porcentajeExito = porcentajeExito;
    }

    public JugadorDTO(Jugador jugador, double porcentajeExito) {
        this.id = jugador.getId();
        this.nombre = jugador.getNombre();
        this.fechaRegistro = jugador.getFechaRegistro();
        this.porcentajeExito = porcentajeExito;
    }

    public JugadorDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPorcentajeExito() {
        return porcentajeExito;
    }

    public void setPorcentajeExito(double porcentajeExito) {
        this.porcentajeExito = porcentajeExito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JugadorDTO that = (JugadorDTO) o;
        return Double.compare(that.porcentajeExito, porcentajeExito) == 0 && Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(fechaRegistro, that.fechaRegistro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,nombre, fechaRegistro,porcentajeExito);
    }

    @Override
    public String toString() {
        return "JugadorDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", porcentajeExito=" + porcentajeExito +
                '}';
    }
}
