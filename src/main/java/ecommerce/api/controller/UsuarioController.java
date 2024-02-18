package ecommerce.api.controller;

import ecommerce.api.dto.UsuarioDTO;
import ecommerce.api.entity.ProdutoEntity;
import ecommerce.api.entity.UsuarioEntity;
import ecommerce.api.repository.UsuarioRepository;
import ecommerce.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioEntity> procurarUsuario(
            @RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "") String telefone){
        return usuarioService.findAllUsuarios();

    }
    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioDTO dados) {
        UsuarioEntity usuario = usuarioService.cadastrarUsuario(dados);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO dados) {
        UsuarioEntity usuarioAtualizado = usuarioService.atualizarUsuario(id, dados);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}
