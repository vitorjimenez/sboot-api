package org.gotech.usuario;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.text.Format;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioService {

    private final UsuarioRepository repository;

    UsuarioService(UsuarioRepository repository){
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

    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            listaUsuarios = repository.findAll();
        } catch (RuntimeException e) {
            System.err.println("Houve uma falha ao consultar os usuarios. " + e.getMessage());
        }
        return ResponseEntity.status(200).body(listaUsuarios);
    }

    public ResponseEntity<String> inserirUsuario(Usuario novoUsuario) {
        String response = "";
        if(Objects.equals(novoUsuario.getNome(), "") ||
                Objects.equals(novoUsuario.getEmail(), "") ||
                Objects.equals(novoUsuario.getSenha(), "")){
            response = "Todos os campos precisam estar preenchidos.";
            return ResponseEntity.status(500).body(response);
        }
        try {
            response = "Sucesso ao inserir o usuario " + novoUsuario.getNome();
            novoUsuario.setCreatedDate(LocalDate.now());
            repository.save(novoUsuario);
        } catch (Exception e) {
            response = "Houve uma falha na insercao do usuario " + e.getMessage();
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body(response);
    }

    public ResponseEntity<String> atualizarUsuario(Usuario atualizarUsuario){
        String response = "";
        if(Objects.equals(atualizarUsuario.getNome(), "") ||
                Objects.equals(atualizarUsuario.getEmail(), "") ||
                Objects.equals(atualizarUsuario.getSenha(), "")){
            response = "Todos os campos precisam estar preenchidos.";
            return ResponseEntity.status(500).body(response);
        }
        try {
            Usuario usuarioAtualizado = findById(atualizarUsuario.getId());
            usuarioAtualizado.setNome(atualizarUsuario.getNome());
            usuarioAtualizado.setEmail(atualizarUsuario.getEmail());
            usuarioAtualizado.setSenha(atualizarUsuario.getSenha());
        } catch (RuntimeException e) {
            response = "Falha na atualizacao do usuario -> " + e.getMessage();
            return ResponseEntity.status(500).body(response);
        }
        return ResponseEntity.status(200).body("Sucesso na atualizacao do usuario " + atualizarUsuario.getNome());
    }

}
