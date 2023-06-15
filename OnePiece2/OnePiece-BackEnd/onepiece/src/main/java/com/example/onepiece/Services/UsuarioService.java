package com.example.onepiece.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.onepiece.Models.ComentarioModel;
import com.example.onepiece.Models.UsuarioModel;
import com.example.onepiece.Repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service

public class UsuarioService {
    
    
    final UsuarioRepository UsuarioRepository;  // ponto de injeção permite que serviço use uma dependencia la do repository

    UsuarioService(UsuarioRepository UsuarioRepository) {
        this.UsuarioRepository = UsuarioRepository;
    }

    public boolean verificaEmail(String email) {
        // Verifica o formato do email utilizando uma expressão regular
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public boolean verificaSenha(String senha) {
    // Verifica se a senha contém pelo menos um número e possui pelo menos 6 caracteres
    return senha.matches(".*\\d.*") && senha.length() >= 6;
}

     @Transactional // por causa de relacionamentos, deleção em cascata etc.
    public UsuarioModel save(UsuarioModel usuarioModel) {
        return UsuarioRepository.save(usuarioModel); // usa o save do jpa.
    }

    public boolean existsByUsuario(String usuario) {
        return UsuarioRepository.existsByUsuario(usuario);         // metodo customizado n tem jpa com ele. 
    }

    public boolean existsByEmail(String email) {
    return UsuarioRepository.existsByEmail(email);
}

    public List<UsuarioModel>  findAll() {
        return UsuarioRepository.findAll();
    }

    public Optional<UsuarioModel> findById(UUID id) {
        return UsuarioRepository.findById(id);  
    }

    public void delete(UsuarioModel usuarioModel) {
         UsuarioRepository.delete(usuarioModel);
    }




 

   







}
