package com.example.onepiece.Models;

import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.List;


@Entity 
@Table(name = "usuarios")
@Data
public class UsuarioModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<ComentarioModel> comentarios;

    public void addComentario(ComentarioModel comentario) {
    comentarios.add(comentario);
    comentario.setUsuario(this);
}
}
