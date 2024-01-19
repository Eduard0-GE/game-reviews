package datadives.io.msreviews.service;

import datadives.io.msreviews.dto.AnaliseDto;
import datadives.io.msreviews.model.Analise;
import datadives.io.msreviews.model.Usuario;
import datadives.io.msreviews.repository.AnaliseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnaliseServiceTest {
    @Mock private AnaliseRepository repository;
    @InjectMocks private AnaliseService service;

    @Test
    public void findAll(){
        List<Analise> analises = Arrays.asList(
                new Analise(1, new Usuario(), "GAME_1_HASH", "GAME_1_ANALISE", (short) 5),
                new Analise(1, new Usuario(), "GAME_2_HASH", "GAME_2_ANALISE", (short) 3)
        );
        when(repository.findAll()).thenReturn(analises);

        List<AnaliseDto> dtos = service.findAll();

        verify(repository, atLeast(1)).findAll();
        assertEquals(analises.get(0).getAnalise(), dtos.get(0).getAnalise());
    }

}