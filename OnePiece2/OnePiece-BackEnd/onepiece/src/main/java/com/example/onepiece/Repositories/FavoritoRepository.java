package com.example.onepiece.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onepiece.Models.FavoritoModel;



public interface FavoritoRepository extends JpaRepository<FavoritoModel, UUID>{

    
}
