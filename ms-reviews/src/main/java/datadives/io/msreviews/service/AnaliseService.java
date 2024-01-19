package datadives.io.msreviews.service;

import datadives.io.msreviews.dto.AnaliseDto;
import datadives.io.msreviews.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnaliseService {
    @Autowired
    private AnaliseRepository repository;

    public List<AnaliseDto> findAll(){
        return repository.findAll().stream()
                .map(AnaliseDto::new)
                .collect(Collectors.toList());
    }
}
