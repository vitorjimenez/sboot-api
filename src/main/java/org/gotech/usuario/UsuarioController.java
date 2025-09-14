package org.gotech.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @GetMapping("/listar")
    List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario novoUsuario){
        System.out.println(novoUsuario.toString());
        repository.save(novoUsuario);
        return ResponseEntity.status(200).body("Sucesso na criação do usuário.");
    }
}
