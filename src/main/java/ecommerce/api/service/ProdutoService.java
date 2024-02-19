package ecommerce.api.service;

import ecommerce.api.dto.ProdutoDTO;
import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.entity.ProdutoEntity;
import ecommerce.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProdutoEntity> pesquisarProdutos(Long id, String nome) {
        if (!nome.isBlank() || id > 0L) return produtoRepository.findByNomeOrId(nome, id);
        return produtoRepository.findAll();
    }

    public ProdutoEntity cadastrarProduto(ProdutoDTO dados) {
        Set<CategoriaEntity> categorias = new HashSet<>();
        dados.categoria().forEach(i -> {
            categorias.add(categoriaService.findById(i.id()));
        });

        ProdutoEntity novoProduto = ProdutoEntity.builder()
                .nome(dados.nome())
                .preco(dados.preco())
                .estoque(dados.estoque())
                .categoria(categorias)
                .build();

        return produtoRepository.save(novoProduto);
    }

    public ProdutoEntity atualizarProduto(Long id, ProdutoDTO dados) {
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Set<CategoriaEntity> categorias = new HashSet<>();
        dados.categoria().forEach(i -> {
            if (i != null && i.id() != null) {
                categorias.add(categoriaService.findById(i.id()));
            }
        });

        ProdutoEntity produtoAtualizado = ProdutoEntity.atualizarProduto(produtoExistente, dados, categorias);
        return produtoRepository.save(produtoAtualizado);
    }

    public void deletarProduto(Long id) {
        ProdutoEntity produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produtoRepository.delete(produtoExistente);
    }
}