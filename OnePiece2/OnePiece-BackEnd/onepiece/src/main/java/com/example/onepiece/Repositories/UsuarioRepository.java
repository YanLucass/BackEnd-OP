package com.example.onepiece.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onepiece.Models.ComentarioModel;
import com.example.onepiece.Models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID> {

    boolean existsByUsuario(String usuario);
    boolean existsByEmail(String email);

    // Método para obter os comentários de um usuário pelo ID
    List<ComentarioModel> findComentariosByid(UUID id);
}
