package datadives.io.msreviews.repository;

import datadives.io.msreviews.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryTest {

    @Mock  private UsuarioRepository repository;

    @BeforeEach
    public void init(){

    }

    @Test
    public void findByEmail_valid_email(){
        Usuario gerson = new Usuario(1, "email@gerson.io", "gersonSenhaForte", "84748671598");
        String email_valido = gerson.getEmail();

        when(repository.findByEmail(email_valido)).thenReturn(Optional.of(gerson));

        Optional<Usuario> user_valido = repository.findByEmail("email@gerson.io");

        verify(repository, atLeast(1)).findByEmail(email_valido);

        assertFalse(user_valido.isEmpty());
        assertEquals(email_valido, user_valido.get().getEmail());
    }

    @Test
    public void findByEmail_invalid_email(){
        String email_invalido = "email@invalido.web";

        when(repository.findByEmail(email_invalido)).thenReturn(Optional.empty());

        Optional<Usuario> user_invalido = repository.findByEmail(email_invalido);

        verify(repository, atLeast(1)).findByEmail(email_invalido);

        assertTrue(user_invalido.isEmpty());
    }

    @Test
    public void findAll(){
        Usuario user1 = new Usuario(1, "email@gerson.io", "gersonSenhaForte", "84748671598");
        Usuario user2 = new Usuario(2, "email@akali.io", "akaliShinobiSenha", "84679841257");
        List<Usuario> usuarios = Arrays.asList(user1, user2);

        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> lista = repository.findAll();

        verify(repository, atLeast(1)).findAll();

        assertFalse(lista.isEmpty());
        assertEquals(user1.getEmail(), lista.get(0).getEmail());
        assertEquals(user2.getSenha(), lista.get(1).getSenha());
        assertEquals(user1, lista.get(0));
        assertEquals(user2, lista.get(1));
    }
}