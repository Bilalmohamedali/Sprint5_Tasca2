package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.services;

import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Jugador;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.JugadorDTO;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Tirada;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.repository.JugadorRepository;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.repository.TiradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JuegoDServiceImpl implements  JuegoDService {
    @Autowired
    JugadorRepository jugadorRepository;
    @Autowired
    TiradaRepository tiradaRepository;

    public JuegoDServiceImpl(JugadorRepository jugadorRepository, TiradaRepository tiradaRepository) {
        this.jugadorRepository = jugadorRepository;
        this.tiradaRepository = tiradaRepository;
    }

    @Override
    public Jugador registrarJugador(Jugador jugadorInput) {
        Jugador jugador = new Jugador();
        jugador.setNombre(jugadorInput.getNombre());
        jugador.setFechaRegistro(LocalDate.now());
        return jugadorRepository.save(jugador);
    }

    @Override
    public String modificarNombreJugador(Jugador jugador) {
        Long num = jugador.getId();
        if (jugadorRepository.findById(num).isPresent()) {
            Jugador jugadorToUpdate = new Jugador();
            jugadorToUpdate.setId(num);
            jugadorToUpdate.setNombre(jugador.getNombre());
            jugadorToUpdate.setFechaRegistro(LocalDate.now());
            jugadorRepository.save(jugadorToUpdate);
            return "Modificado correctamente";
        }
        return "No se pudo modificar";
    }

    @Override
    public String eliminarTiradas(Jugador jugador) {
        Long num = jugador.getId();
        if (jugadorRepository.findById(num).isPresent()) {
            tiradaRepository.deleteByJugador(jugador);
            return "tiradas jugador " + jugador.getNombre() + " borradas";
        }
        return "no se pudo encontrar al jugador";
    }


    public List<Tirada> verTiradas(Jugador jugador) {
        Long num = jugador.getId();
        if (jugadorRepository.findById(num).isPresent()) {
            return tiradaRepository.findByJugador(jugador);
        }
        ArrayList<Tirada> vacia = new ArrayList<>();
        return vacia;
    }

    @Override
    public Optional<Jugador> findJugadorById(Long id) {
        if (jugadorRepository.findById(id).isPresent()) {
            Optional<Jugador> jugador = jugadorRepository.findById(id);
            return jugador;
        }
        return Optional.empty();
    }

    public Tirada realizarTirada(Jugador jugador) {
        Tirada tirada = new Tirada();
        tirada.setDado1(randomDado());
        tirada.setDado2(randomDado());
        tirada.setJugador(jugador);
        if (tirada.getDado1() + tirada.getDado2() >= 7) {
            tirada.setVictoria(true);
        } else {
            tirada.setVictoria(false);
        }
        return tiradaRepository.save(tirada);
    }

    @Override
    public List<JugadorDTO> verMediaJugadores() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        List<JugadorDTO> jugadoresDTO = new ArrayList<>();

        jugadores.forEach(x -> {
            double media = calcularPorcentajeExito(x);
            JugadorDTO jugadorDTO = new JugadorDTO(x, media);
            jugadoresDTO.add(jugadorDTO);
        });
        return jugadoresDTO;
    }

    @Override
    public Double porcentajeMedio() {
        double porcentajeExito;
        List<Tirada> listaTiradas = new ArrayList<>();
        listaTiradas = tiradaRepository.findAll();
        double exitos = listaTiradas.stream().filter(tirada -> tirada.isVictoria() == true).count();
        double total = listaTiradas.size();
        porcentajeExito = (exitos / total) * 100;
        return Double.valueOf(Math.round(porcentajeExito));
    }

    @Override
    public JugadorDTO menorPorcentaje() {
        List<JugadorDTO> jugadores = verMediaJugadores();
        return jugadores
                .stream()
                .min(Comparator.comparing(JugadorDTO::getPorcentajeExito))
                .get()
                ;
    }

    @Override
    public JugadorDTO mayorPorcentaje() {
        List<JugadorDTO> jugadores = verMediaJugadores();
        return jugadores
                .stream()
                .max(Comparator.comparing(JugadorDTO::getPorcentajeExito))
                .get()
                ;
    }

    public double calcularPorcentajeExito(Jugador jugador) {
        List<Tirada> tiradas = tiradaRepository.findByJugador(jugador);
        int numTiradas = tiradas.size();
        int numTiradasGanadas = 0;
        for (Tirada tirada : tiradas) {
            if (tirada.isVictoria()) {
                numTiradasGanadas++;
            }
        }
        return (double) numTiradasGanadas / numTiradas * 100;
    }


    public double calcularPorcentajeExitoMedio() {
        List<Jugador> jugadores = jugadorRepository.findAll();
        double porcentajeExitoTotal = 0;
        for (Jugador jugador : jugadores) {
            porcentajeExitoTotal += calcularPorcentajeExito(jugador);
        }
        return porcentajeExitoTotal / jugadores.size();
    }


        private int randomDado() {
            return ThreadLocalRandom.current().nextInt(1, 7);
        }
    }
