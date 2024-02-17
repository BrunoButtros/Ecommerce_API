package ecommerce.api.entity;
import ecommerce.api.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table(name = "produtos")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class ProdutoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private int estoque;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<CategoriaEntity> categoria;


    public static ProdutoEntity criarProduto(ProdutoDTO dados, Set<CategoriaEntity> categoria) {
        return ProdutoEntity.builder()
                .nome(dados.nome())
                .preco(dados.preco())
                .estoque(dados.estoque())
                .categoria(categoria)
                .build();
    }

    public static ProdutoEntity atualizarProduto(ProdutoEntity produto, ProdutoDTO dados, Set<CategoriaEntity> categoria) {
        produto.setNome(dados.nome());
        produto.setPreco(dados.preco());
        produto.setEstoque(dados.estoque());
        produto.setCategoria(categoria);
        return produto;

    }
}
