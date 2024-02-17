package ecommerce.api.repository;

import ecommerce.api.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByNome(String nome);

    List<ProdutoEntity> findByNomeOrId(String nome, Long id);
}
