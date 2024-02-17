package ecommerce.api.controller;

import ecommerce.api.dto.UsuarioDTO;
import ecommerce.api.entity.UsuarioEntity;
import ecommerce.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastar(@RequestBody UsuarioDTO dados) {

        var user = new UsuarioEntity();

        user.setEmail(dados.email());
        user.setNome((dados.nome()));
        user.setTelefone(dados.telefone());
        user.setSenha(dados.senha());

    repository.save(user);
    return new ResponseEntity<UsuarioEntity>(user, HttpStatus.CREATED);

    }

}
