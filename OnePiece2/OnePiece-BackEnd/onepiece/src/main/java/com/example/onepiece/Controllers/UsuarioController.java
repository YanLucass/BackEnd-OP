package com.example.onepiece.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)  // permite que seja acessado de qualquer fonte



public class UsuarioController {
    
    final UsuarioService UsuarioService;

    public UsuarioController(com.example.onepiece.Services.UsuarioService usuarioService) {
        UsuarioService = usuarioService;
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioModel> saveProduct(@RequestBody @Valid UsuarioDTO usuarioDTO) {

       if(UsuarioService.existsByUsuario(usuarioDTO.getUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
       }

         
      if (UsuarioService.existsByEmail(usuarioDTO.getEmail())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }


        var UsuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioDTO, UsuarioModel); 
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioService.save(UsuarioModel));
    }


        @GetMapping("/usuario")
        public ResponseEntity<List<UsuarioModel>> getAllUsuarios() {
            List<UsuarioModel> usuarios = UsuarioService.findAll();
            if (usuarios.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usuarios);
        }
    
     @GetMapping("/usuario/{id}")  // entre chaves porque vai ser numeros aleatorios de acordo com os ids gerados.  Vamos obter ele.
      
        public ResponseEntity<Object> getOneusuario(@PathVariable(value = "id") UUID id) {
            Optional<UsuarioModel> usuarioModelOptional = UsuarioService.findById(id);
                if(!usuarioModelOptional.isPresent()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não achamos esse ID");
                }

                return ResponseEntity.status(HttpStatus.OK).body(usuarioModelOptional.get());

        }

         @DeleteMapping("/usuario/{id}")

        public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id) {
            Optional<UsuarioModel> usuarioModelOptional = UsuarioService.findById(id);
            if(!usuarioModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usarui n existe");
            }

            UsuarioService.delete(usuarioModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado.");
            


        }

       @PutMapping("/usuario/{id}")

       public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDTO usuarioDTO) {

            Optional<UsuarioModel> usuarioModelOptional = UsuarioService.findById(id);

            if(!usuarioModelOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

            }

            var usuarioModel = new UsuarioModel();
            BeanUtils.copyProperties(usuarioDTO, usuarioModel);
            usuarioModel.setId(usuarioModelOptional.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(UsuarioService.save(usuarioModel));
           

       }
      

}



