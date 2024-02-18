package ecommerce.api.repository;

import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByIdOrNomeOrEmailOrTelefone(Long id, String nome,String email,String telefone);





}
