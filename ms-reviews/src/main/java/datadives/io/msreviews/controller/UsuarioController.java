package datadives.io.msreviews.controller;

import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.UsuarioRepository;
import datadives.io.msreviews.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/user/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email){
        try {
            return ResponseEntity.ok().body(service.findByEmail(email));
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<String>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping("/user")
    public ResponseEntity<String> create(@RequestBody Usuario user){
        try {
            service.create(user);
            return ResponseEntity.status(201).body("Usuario criado com sucesso");
        }catch (ResponseStatusException e){
            return new ResponseEntity<>("Usuário já existe", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<String> update(@RequestBody Usuario user){
        try {
            service.update(user);
            return ResponseEntity.status(200).body("Usuario atualizado com sucesso");
        }catch (ResponseStatusException e){
            return new ResponseEntity<>("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/email/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email){
        try {
            service.deleteByEmail(email);
            return ResponseEntity.ok().body("Usuario deletado com sucesso");
        }catch (ResponseStatusException e){
            return new ResponseEntity<>("Falha ao deletar usuário", HttpStatus.NOT_FOUND);
        }
    }
}
