package com.example.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioDTO {

      @NotBlank
      private String usuario;
      @Email
      private String email;
      @Size(min = 7)
      private String password;
}
