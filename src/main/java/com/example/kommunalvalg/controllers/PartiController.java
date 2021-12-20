package com.example.kommunalvalg.controllers;
import com.example.kommunalvalg.models.Parti;
import com.example.kommunalvalg.repositories.partiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PartiController {

@Autowired
partiRepo PartiRepo;

    @GetMapping("/getAllParties")
    public List<Parti> getAllParties(){
        return PartiRepo.findAll();
    }

    @PostMapping("/createParti")
    public Parti createParti(@RequestBody Parti parti){
        return PartiRepo.save(parti);
    }

    @GetMapping("/getParti/{id}")
    public Parti getParti(@PathVariable Long id){
        return PartiRepo.findById(id).get();
    }

}
