package ecommerce.api.controller;

import ecommerce.api.dto.CategoriaDTO;
import ecommerce.api.entity.CategoriaEntity;
import ecommerce.api.repository.CategoriaRepository;
import ecommerce.api.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;
@Autowired
private CategoriaService categoriaService;
    @GetMapping
    public List<CategoriaEntity> pesquisarCategorias(
            @RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam(required = false, name = "apelido", defaultValue = "") String nome) {
        return categoriaService.pesquisarCategorias(id, nome);
    }

    @PostMapping
    public ResponseEntity<CategoriaEntity> criarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaSalva = categoriaService.criarCategoria(categoriaDTO);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaEntity> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCategoria(@PathVariable Long id) {
        categoriaService.excluirCategoria(id);
    }
}
