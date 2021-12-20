package com.example.kommunalvalg.repositories;

import com.example.kommunalvalg.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface candidateRepo extends JpaRepository<Candidate,Long> {


}
