package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.services;

import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Jugador;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.JugadorDTO;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Tirada;

import java.util.List;
import java.util.Optional;

public interface JuegoDService {

    Jugador registrarJugador(Jugador jugador);

    String modificarNombreJugador(Jugador jugador);

    String eliminarTiradas(Jugador jugador);

    List<Tirada> verTiradas(Jugador jugador);

    Optional<Jugador> findJugadorById(String  id);

    Tirada realizarTirada(Jugador jugador);

    List<JugadorDTO> verMediaJugadores();

    Double porcentajeMedio();

    JugadorDTO menorPorcentaje();

    JugadorDTO mayorPorcentaje();
}
