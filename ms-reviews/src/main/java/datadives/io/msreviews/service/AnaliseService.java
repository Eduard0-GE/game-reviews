package datadives.io.msreviews.service;

import datadives.io.msreviews.dto.AnaliseDto;
import datadives.io.msreviews.model.Analise;
import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.AnaliseRepository;
import datadives.io.msreviews.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnaliseService {
    @Autowired
    private AnaliseRepository analiseRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void create(AnaliseDto dto){
        Usuario user = usuarioRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado")
        );
        analiseRepository.save(new Analise(user, dto));
    }
    public List<AnaliseDto> findAll(){
        return analiseRepository.findAll().stream()
                .map(AnaliseDto::new)
                .collect(Collectors.toList());
    }
}
