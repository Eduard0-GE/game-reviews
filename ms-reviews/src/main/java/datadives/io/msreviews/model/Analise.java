package datadives.io.msreviews.model;

import datadives.io.msreviews.dto.AnaliseDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

@Entity
@Table(name = "analises")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Analise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analise_id")
    private int analiseId;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuarioId;
    @Column(nullable = false)
    private String jogo;
    @Column(nullable = false)
    private String analise;
    @Column(nullable = false)
    private short nota;

    public Analise(Usuario user, AnaliseDto dto){
        this.usuarioId = user;
        this.jogo = dto.getJogo();
        this.analise = dto.getAnalise();
        this.nota = dto.getNota();
    }
}
