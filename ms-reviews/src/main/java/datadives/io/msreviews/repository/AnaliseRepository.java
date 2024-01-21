package datadives.io.msreviews.repository;

import datadives.io.msreviews.model.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise,Integer> {

    Optional<Analise> findByJogoAndUsuarioId_email(String jogo, String email);
    List<Analise> findAll();
}
