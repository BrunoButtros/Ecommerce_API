package ecommerce.api.service;

import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    public List<CategoriaEntity> pesquisarCategorias(Long id, String nome){
        if (!nome.isBlank() || id > 0L) return categoriaRepository.findByNomeOrId(nome, id);
        return categoriaRepository.findAll();

    }



}
