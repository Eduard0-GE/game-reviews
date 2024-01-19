package datadives.io.msreviews.service;

import datadives.io.msreviews.dto.UsuarioDto;
import datadives.io.msreviews.mapper.UsuarioMapper;
import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.UsuarioRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;
    @Mock
    private UsuarioMapper mapper;
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
        Usuario user1 = new Usuario(1, "email@gerson.com", "senhaForte", "telefoneG");
        Usuario user2 = new Usuario(2, "email@mario.com", "senhaFraca", "telefoneM");
        List<Usuario> usuarioList = Arrays.asList(user1, user2);

        when(repository.findAll()).thenReturn(usuarioList);

        List<String> emailList = service.findAll();

        verify(repository, atLeast(1)).findAll();
        assertEquals(usuarioList.get(0).getEmail(), emailList.get(0));
        assertEquals(usuarioList.get(1).getEmail(), emailList.get(1));
    }

    @Test
    void create_valid_user(){
        Usuario user = new Usuario(1, "email@gerson.com", "senhaForte", "telefone");
        when(repository.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> service.create(user));
        verify(repository, atLeast(1)).save(user);
    }
    @Test
    void create_invalid_user(){
        Usuario user = new Usuario(1, "email@gerson.com", "senhaForte", "telefone");
        doThrow(DataIntegrityViolationException.class).when(repository).save(user);

        assertThrows(ResponseStatusException.class, () -> service.create(user));
        verify(repository, atLeast(1)).save(user);
    }

    @Test
    void update_user_that_dont_exists(){
        Usuario user = new Usuario(99, "email@gerson.com", "senhaForte", "telefone");
        when(repository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.update(user));

        verify(repository, atLeast(1)).findById(user.getUsuarioId());
        verify(repository, never()).save(user);
    }

    @Test
    void update_user_that_exists(){
        Usuario user = new Usuario(1, "email@gerson.com", "senhaForte", "telefone");
        when(repository.findById(1)).thenReturn(Optional.of(user));
        when(mapper.updateUsuarioFromUsuario(user, user)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);

        assertDoesNotThrow(() -> service.update(user));

        verify(repository, atLeast(1)).findById(user.getUsuarioId());
        verify(repository, atLeast(1)).save(user);
    }

    @Test
    void deleteByEmail_user_exists(){
        Usuario user = new Usuario(1, "email@valid.com", "senhaForte", "telefone");
        when(repository.deleteByEmail("email@valid.com")).thenReturn(user);

        assertDoesNotThrow(() -> service.deleteByEmail("email@valid.com"));
        verify(repository, atLeast(1)).deleteByEmail("email@valid.com");
    }

    @Test
    void deleteByEmail_user_does_not_exist(){
        Usuario user = new Usuario(1, "email@invalid.com", "senhaForte", "telefone");
        doThrow(EmptyResultDataAccessException.class).when(repository).deleteByEmail("email@invalid.com");

        assertThrows(ResponseStatusException.class, () -> service.deleteByEmail("email@invalid.com"));
        verify(repository, atLeast(1)).deleteByEmail("email@invalid.com");
    }
}