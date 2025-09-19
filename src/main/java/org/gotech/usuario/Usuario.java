package org.gotech.usuario;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="usuario")
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    private String nome;
    private String email;
    private String senha;
    private LocalDate createdDate;

    public Usuario(){}


    public Usuario(Long id, String nome, String email, String senha, LocalDate createdDate) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.createdDate = createdDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
