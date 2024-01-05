package datadives.io.msreviews.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int usuarioId;
    private String email;
    private String senha;
    private String telefone;
}
