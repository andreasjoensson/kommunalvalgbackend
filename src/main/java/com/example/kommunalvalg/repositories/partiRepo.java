package com.example.kommunalvalg.repositories;


import com.example.kommunalvalg.models.Parti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface partiRepo extends JpaRepository<Parti,Long> {

}
