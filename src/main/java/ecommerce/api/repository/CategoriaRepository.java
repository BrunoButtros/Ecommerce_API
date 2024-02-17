package ecommerce.api.repository;


import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    List<CategoriaEntity> findByNomeOrId(String nome, Long id);
}