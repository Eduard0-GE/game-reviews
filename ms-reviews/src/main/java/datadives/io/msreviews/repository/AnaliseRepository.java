package datadives.io.msreviews.repository;

import datadives.io.msreviews.model.Analise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise,Integer> {

    List<Analise> findAll();
}
