package com.example.onepiece.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.onepiece.Models.ComentarioModel;
import com.example.onepiece.Models.UsuarioModel;
import com.example.onepiece.Repositories.ComentarioRepository;
import com.example.onepiece.Repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class ComentarioService {

  private final ComentarioRepository comentarioRepository;
  private final UsuarioRepository usuarioRepository;

  public ComentarioService(ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository) {
    this.comentarioRepository = comentarioRepository;
    this.usuarioRepository = usuarioRepository;
  }

  public ComentarioModel save(ComentarioModel comentario) {
    return comentarioRepository.save(comentario);
  }

  public Optional<ComentarioModel> findById(UUID id) {
    return comentarioRepository.findById(id);
  }

  public List<ComentarioModel> findAll() {
    return comentarioRepository.findAll();
  }

  public void delete(ComentarioModel comentario) {
    comentarioRepository.delete(comentario);
  }

  public List<ComentarioModel> getComentariosByid(UUID id) {
    return usuarioRepository.findComentariosByid(id);
  }

  @Transactional
  public ComentarioModel addComentario(UUID id, ComentarioModel comentarioModel) {
    Optional<UsuarioModel> usuarioModelOptional = usuarioRepository.findById(id);
    if (usuarioModelOptional.isPresent()) {
        UsuarioModel usuario = usuarioModelOptional.get();
        comentarioModel.setUsuario(usuario);
        usuario.getComentarios().add(comentarioModel);
        usuarioRepository.save(usuario);
        return comentarioModel;
    }
    return null;
  }

  // Outros métodos personalizados podem ser adicionados de acordo com suas necessidades

}
