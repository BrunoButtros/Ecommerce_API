package ecommerce.api.controller;


import ecommerce.api.dto.ProdutoDTO;
import ecommerce.api.entity.ProdutoEntity;
import ecommerce.api.repository.CategoriaRepository;
import ecommerce.api.repository.ProdutoRepository;
import ecommerce.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    CategoriaRepository categoriaRepository;


    @GetMapping
    public List<ProdutoEntity> findid(@RequestParam(required = false, defaultValue = "0") Long id,
                                      @RequestParam(required = false, name = "apelido", defaultValue = "") String nome) {
        return produtoService.pesquisarProdutos(id, nome);
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> cadastroProduto(@Valid @RequestBody ProdutoDTO dados) {
        ProdutoEntity produtoSalvo = produtoService.cadastrarProduto(dados);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoEntity> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dados) {
        ProdutoEntity produtoAtualizado = produtoService.atualizarProduto(id, dados);
        return ResponseEntity.ok(produtoAtualizado);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
