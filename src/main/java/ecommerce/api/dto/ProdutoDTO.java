package ecommerce.api.dto;


import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ProdutoDTO(Long id, String nome, double preco, int estoque,
                         @NotEmpty(message = "A categoria não pode estar vazia") List<CategoriaDTO> categoria) {


}