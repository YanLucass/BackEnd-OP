    package com.example.onepiece.Models;

    import java.util.UUID;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.Table;
    import lombok.Data;

    @Entity
    @Table(name = "comentarios")
    @Data
    public class ComentarioModel {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false)
        private String conteudo;

        @ManyToOne
        private UsuarioModel usuario;

        public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    }
