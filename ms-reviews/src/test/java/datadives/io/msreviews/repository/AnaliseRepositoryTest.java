package datadives.io.msreviews.repository;

import datadives.io.msreviews.model.Analise;
import datadives.io.msreviews.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnaliseRepositoryTest {

    @Mock private AnaliseRepository repository;

    @Test
    public void findAll(){
        Usuario usuario = new Usuario(1, "email@gerson.io", "gersonSenhaForte", "84748671598");
        Analise analise = new Analise(1 ,usuario, "658dba32787787532e4f7ff1", "BloodBorne é o melhor soulslike lançado pela FromSoftware", (short) 5);

        List<Analise> lista = Arrays.asList(analise);

        when(repository.findAll()).thenReturn(lista);

        List<Analise> nova_lista = repository.findAll();

        verify(repository, atLeast(1)).findAll();

        assertFalse(nova_lista.isEmpty());
        assertEquals(analise, nova_lista.get(0));
    }
}
