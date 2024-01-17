package datadives.io.msreviews.service;

import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    private UsuarioService service;

    @Test
    void findByEmail_user_exists() {
        Usuario user = new Usuario(1, "email@gerson.com", "senhaForte", "telefone");
        when(repository.findByEmail("email@gerson.com")).thenReturn(Optional.of(user));

        Usuario body = service.findByEmail("email@gerson.com");

        verify(repository, atLeast(1)).findByEmail("email@gerson.com");
        assertEquals(user.getEmail(), body.getEmail());
    }

    @Test
    void findByEmail_user_does_not_exists() {
        Usuario user = new Usuario(1, "email@gerson.com", "senhaForte", "telefone");
        when(repository.findByEmail("invalid_email")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.findByEmail("invalid_email"));

        verify(repository, atLeast(1)).findByEmail("invalid_email");
    }

    @Test
    void findAll() {
    }

    @Test
    void save(){

    }
}