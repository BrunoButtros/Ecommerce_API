package ecommerce.api.service;

import ecommerce.api.dto.CategoriaDTO;
import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> pesquisarCategorias(Long id, String nome) {
        if (!nome.isBlank() || id > 0L) return categoriaRepository.findByNomeOrId(nome, id);
        return categoriaRepository.findAll();

    }

    public CategoriaEntity criarCategoria(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoria = CategoriaEntity.builder()
                .nome(categoriaDTO.nome())
                .build();
        return categoriaRepository.save(categoria);


    }

    public CategoriaEntity atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaExistente.setNome(categoriaDTO.nome());

        return categoriaRepository.save(categoriaExistente);


    }

    public void excluirCategoria(Long id) {
        CategoriaEntity categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoriaRepository.delete(categoriaExistente);

    }

}
