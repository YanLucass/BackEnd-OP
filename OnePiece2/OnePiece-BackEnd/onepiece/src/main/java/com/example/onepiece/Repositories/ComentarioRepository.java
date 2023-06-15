package com.example.onepiece.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onepiece.Models.ComentarioModel;



public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID>{

    
}
