package com.example.onepiece.Controllers;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.ComentarioDTO;
import com.example.dtos.UsuarioDTO;
import com.example.onepiece.Models.ComentarioModel;
import com.example.onepiece.Models.UsuarioModel;
import com.example.onepiece.Repositories.UsuarioRepository;
import com.example.onepiece.Services.UsuarioService;

import jakarta.validation.Valid;
import com.example.onepiece.Services.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ComentarioModel>> getComentariosByUsuarioId(@PathVariable(value = "id") UUID id) {
        List<ComentarioModel> comentarios = comentarioService.getComentariosByid(id);
        return ResponseEntity.ok(comentarios);
    }

   @PostMapping("/usuario/{id}/adicionar")
public ResponseEntity<ComentarioModel> addComentarioToUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid ComentarioDTO comentarioDTO) {
    ComentarioModel novoComentario = new ComentarioModel();
    novoComentario.setConteudo(comentarioDTO.getConteudo());

    ComentarioModel comentarioAdicionado = comentarioService.addComentario(id, novoComentario);
    if (comentarioAdicionado != null) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioAdicionado);
    } else {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}

}
