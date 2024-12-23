package br.com.academy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.academy.model.Usuario;

import org.springframework.data.jpa.repository.Query;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    
    @Query("select i from Usuario i where i.email = :email")
    public Usuario findByEmail(String email);

    @Query("select j from Usuario j where j.user = :user and j.senha = :senha")
    public Usuario buscarLogin(String user, String senha);
}
