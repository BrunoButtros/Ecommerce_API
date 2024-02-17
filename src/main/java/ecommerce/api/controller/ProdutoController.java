package ecommerce.api.controller;


import ecommerce.api.dto.ProdutoDTO;
import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.entity.ProdutoEntity;
import ecommerce.api.repository.CategoriaRepository;
import ecommerce.api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @GetMapping
        public List<ProdutoEntity> findid(@RequestParam(required = false, defaultValue = "0") Long id,
                                      @RequestParam(required = false, name = "apelido", defaultValue = "") String nome) {
        if (!nome.isBlank() || id > 0L) return produtoRepository.findByNomeOrId(nome, id);
        return produtoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> cadastroProduto(@Valid @RequestBody ProdutoDTO dados) {
        Set<CategoriaEntity> categorias= new HashSet<>();
        dados.categoria().forEach(i -> {
            categorias.add(categoriaRepository.findById(i.id())
                    .orElseThrow(()-> new RuntimeException("Categoria não encontrada")));

        });


        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .nome(dados.nome())
                .preco(dados.preco())
                .estoque(dados.estoque())
                .categoria(categorias)
                .build();

        ProdutoEntity produtoSalvo = produtoRepository.save(novoProduto);

        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dados) {
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Set<CategoriaEntity> categoria = new HashSet<>();

        dados.categoria().forEach(i -> {
            if (i != null && i.id() != null) {
                        categoria.add(categoriaRepository.findById(i.id())
                                .orElseThrow(() -> new RuntimeException("Categoria não encontrada")));
                    }
                });

        ProdutoEntity produtoAtualizado = ProdutoEntity.atualizarProduto(produtoExistente, dados, categoria);
        ProdutoEntity produtoSalvo = produtoRepository.save(produtoAtualizado);

        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id){
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    produtoRepository.delete(produtoExistente);

    }

}
