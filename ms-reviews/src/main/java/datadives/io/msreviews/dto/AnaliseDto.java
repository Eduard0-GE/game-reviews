package datadives.io.msreviews.dto;

import datadives.io.msreviews.model.Analise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnaliseDto {
    private String email;
    private String jogo;
    private String analise;
    private short nota;

    public AnaliseDto(Analise analise){
        this.email = analise.getUsuarioId().getEmail();
        this.jogo = analise.getJogo();
        this.analise = analise.getAnalise();
        this.nota = analise.getNota();
    }
}
