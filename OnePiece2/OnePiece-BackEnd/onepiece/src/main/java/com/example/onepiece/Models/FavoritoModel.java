package com.example.onepiece.Models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favoritos")

public class FavoritoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String arco;

    @Column(nullable = false, unique = true)
    private String preferido;

    @Column(nullable = false, unique = true)
    private String personagem;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
}