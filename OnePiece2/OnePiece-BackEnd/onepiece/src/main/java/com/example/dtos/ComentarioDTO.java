package com.example.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComentarioDTO {

    @NotBlank
    private String conteudo;

    // Outros campos do comentário, se houver
}
