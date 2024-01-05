package datadives.io.msreviews.controller;

import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/user/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email){
        return ResponseEntity.ok().body(repository.findByEmail(email).get());
    }

    @GetMapping("/user")
    public ResponseEntity<List<String>> findAll(){
        List<String> emails = repository.findAll().stream()
                .map(Usuario::getEmail)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(emails);
    }

}
