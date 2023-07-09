package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.repository;

import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Tirada;
import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Jugador;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TiradaRepository extends MongoRepository<Tirada, String> {

    List<Tirada> findByJugador(Jugador jugador);
    List<Tirada> findAll();

    void deleteByJugador(Jugador jugador);
}
