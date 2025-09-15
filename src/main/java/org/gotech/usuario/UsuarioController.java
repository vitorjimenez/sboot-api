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

    private final UsuarioService service;

    UsuarioController(UsuarioService usuarioService){
        this.service = usuarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return service.listarUsuarios();
    }

    @PostMapping("/criar")
    @Transactional
    public ResponseEntity<String> inserirUsuario(@RequestBody Usuario novoUsuario){
        return service.inserirUsuario(novoUsuario);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario atualizarUsuario){
        return service.atualizarUsuario(atualizarUsuario);
    }
}
