package org.gotech.usuario;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    public Usuario findById(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isPresent()){
            return usuario.get();
        } else {
            throw  new RuntimeException();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario novoUsuario){
        try {
            repository.save(novoUsuario);
            return ResponseEntity.status(200).body("Sucesso na criação do usuário -> " + novoUsuario.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Houve um erro na criacao do usuário. Erro: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario atualizarUsuario){
        Usuario usuarioAtualizado = findById(atualizarUsuario.getId());
        usuarioAtualizado.setNome(atualizarUsuario.getNome());
        usuarioAtualizado.setEmail(atualizarUsuario.getEmail());
        usuarioAtualizado.setSenha(atualizarUsuario.getSenha());
        return ResponseEntity.status(200).body("Sucesso na atualizacao do usuario -> " + usuarioAtualizado.getNome());
    }
}
