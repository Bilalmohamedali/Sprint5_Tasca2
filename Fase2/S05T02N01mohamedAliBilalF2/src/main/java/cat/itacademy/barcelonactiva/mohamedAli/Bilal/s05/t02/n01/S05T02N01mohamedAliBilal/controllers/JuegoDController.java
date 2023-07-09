package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.controllers;


import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.JugadorDTO;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Tirada;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.services.JuegoDService;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/juego")
public class JuegoDController {
    @Autowired
    JuegoDService juegoDService;

    public JuegoDController() {
    }

    @PostMapping("/players") // POST: /players: crea un jugador/a.
    public ResponseEntity<Jugador> registrarJugador(@RequestBody(required = false) Jugador jugador){
        if (jugador == null){
            Jugador jugadorAnon = new Jugador("Anonimo");
            return ResponseEntity.status(HttpStatus.CREATED).body(juegoDService.registrarJugador(jugadorAnon));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoDService.registrarJugador(jugador));
    }

    @PutMapping("/players") // PUT /players: modifica el nom del jugador/a.
    public ResponseEntity<?> updateJugador(@RequestBody Jugador jugador){
        Optional<Jugador> jugadorDatos = juegoDService.findJugadorById(jugador.getId()).stream().findFirst();
        if (jugadorDatos.isPresent()){
            Jugador _jugador = jugadorDatos.get();
            _jugador.setNombre(jugador.getNombre());
            return ResponseEntity.status(HttpStatus.OK).body(juegoDService.modificarNombreJugador(_jugador));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra al jugador");
        }
    }

    @PostMapping("/players/{id}/games") // POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
    public ResponseEntity<Tirada> realizarTirada(@PathVariable("id") String id){
        Optional<Jugador> jugadorDatos = juegoDService.findJugadorById(id).stream().findFirst();
        if (jugadorDatos.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(juegoDService.realizarTirada(jugadorDatos.get()));
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    @DeleteMapping("/players/{id}/games") // DELETE /players/{id}/games: elimina les tirades del jugador/a.
    public ResponseEntity<?> eliminarTiradas(@PathVariable String id){
        Optional<Jugador> jugadorDatos = juegoDService.findJugadorById(id).stream().findFirst();
        if (jugadorDatos.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(juegoDService.eliminarTiradas(jugadorDatos.get()));
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/players") // GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.
    public ResponseEntity<?> getAllPlayersWithinWins(){
        try{
            List<JugadorDTO> jugadores = new ArrayList<JugadorDTO>();
            juegoDService.verMediaJugadores().forEach(jugadores::add);
            if (jugadores.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Sin resultados");
            }
            return ResponseEntity.status(HttpStatus.OK).body(jugadores);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("players/{id}/games") // GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
    public ResponseEntity<?> getAllGamesByPlayer(@PathVariable String id){
        Optional<Jugador> jugadorDatos = juegoDService.findJugadorById(id).stream().findFirst();
        if (jugadorDatos.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(juegoDService.verTiradas(jugadorDatos.get()));
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("players/ranking") // GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
    public ResponseEntity<?> getAverageScore(){
        return ResponseEntity.status(HttpStatus.OK).body(juegoDService.porcentajeMedio());
    }

    @GetMapping("/players/ranking/loser") // GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
    public ResponseEntity<?> getLowerScore(){
        return ResponseEntity.status(HttpStatus.OK).body(juegoDService.menorPorcentaje());
    }

    @GetMapping("/players/ranking/winner") // GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit.
    public ResponseEntity<?> getHighestScore(){
        return ResponseEntity.status(HttpStatus.OK).body(juegoDService.mayorPorcentaje());
    }


}
