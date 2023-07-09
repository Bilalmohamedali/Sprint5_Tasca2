package cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.repository;

import cat.itacademy.barcelonactiva.mohamedAli.Bilal.s05.t02.n01.S05T02N01mohamedAliBilal.model.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
