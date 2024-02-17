package ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "categorias")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
