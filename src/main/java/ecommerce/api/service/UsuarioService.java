package ecommerce.api.service;

import ecommerce.api.dto.UsuarioDTO;
import ecommerce.api.entity.UsuarioEntity;
import ecommerce.api.repository.UsuarioRepository;
import ecommerce.api.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    public List<UsuarioEntity> findAllUsuarios(UsuarioDTO usuarioDTO) {


        if (usuarioDTO.id() > 0
                || !usuarioDTO.nome().isEmpty()
                || !usuarioDTO.email().isEmpty()
                || !usuarioDTO.telefone().isEmpty()) {
            return usuarioRepository.findByIdOrNomeOrEmailOrTelefone(
                    usuarioDTO.id(),
                    usuarioDTO.nome(),
                    usuarioDTO.email(),
                    usuarioDTO.telefone());
        } else {

            return usuarioRepository.findAll();

        }
    }

    public UsuarioEntity cadastrarUsuario(UsuarioDTO dados) {
        UsuarioValidator.validate(dados);
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setTelefone(dados.telefone());

        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity atualizarUsuario(Long id, UsuarioDTO dados) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        usuarioExistente.setEmail(dados.email());
        usuarioExistente.setTelefone(dados.telefone());
        return usuarioRepository.save(usuarioExistente);
    }



    public void deletarUsuario(Long id) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuarioExistente);
    }
}