package ecommerce.api.entity;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "UsuarioJPA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class UsuarioEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
 }
