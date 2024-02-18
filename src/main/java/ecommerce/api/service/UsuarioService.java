package ecommerce.api.service;

import ecommerce.api.dto.CategoriaDTO;
import ecommerce.api.dto.UsuarioDTO;
import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.entity.UsuarioEntity;
import ecommerce.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    public List<UsuarioEntity> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public UsuarioEntity findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<UsuarioEntity> findUsuariosByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public List<UsuarioEntity> findUsuariosByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<UsuarioEntity> findUsuariosByTelefone(String telefone) {
        return usuarioRepository.findByTelefone(telefone);
    }





    public UsuarioEntity cadastrarUsuario(UsuarioDTO dados) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setTelefone(dados.telefone());

        return usuarioRepository.save(usuario);
    }

    public UsuarioEntity atualizarUsuario(Long id, UsuarioDTO dados) {
        UsuarioEntity usuarioExistente = findUsuarioById(id);
        usuarioExistente.setEmail(dados.email());
        usuarioExistente.setTelefone(dados.telefone());
        return usuarioRepository.save(usuarioExistente);
    }
    public void deletarUsuario(Long id) {
        UsuarioEntity usuarioExistente = findUsuarioById(id);
        usuarioRepository.delete(usuarioExistente);
    }
}