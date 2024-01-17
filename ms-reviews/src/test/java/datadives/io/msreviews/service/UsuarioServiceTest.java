package datadives.io.msreviews.service;

import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        when(repository.findByEmail("valid_email")).thenReturn(Optional.of(user));

        Usuario body = service.findByEmail("valid_email");

        verify(repository, atLeast(1)).findByEmail("valid_email");
        assertEquals(user.getEmail(), body.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void save(){

    }
}