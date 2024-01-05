package datadives.io.msreviews.controller;

import datadives.io.msreviews.dto.AnaliseDto;
import datadives.io.msreviews.model.Analise;
import datadives.io.msreviews.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AnaliseController {
    @Autowired
    private AnaliseRepository repository;

    @GetMapping("/analise")
    public ResponseEntity<List<AnaliseDto>> findAll(){
        List<AnaliseDto> analises = repository.findAll()
                .stream()
                .map(e -> new AnaliseDto(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(analises);
    }
}
