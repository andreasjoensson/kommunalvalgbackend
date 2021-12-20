package com.example.kommunalvalg.controllers;

import com.example.kommunalvalg.models.Candidate;
import com.example.kommunalvalg.models.Parti;
import com.example.kommunalvalg.repositories.candidateRepo;
import com.example.kommunalvalg.repositories.partiRepo;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CandidateController {

@Autowired
candidateRepo candRepo;

@Autowired
partiRepo partiesRepo;


@GetMapping("/getAllCandidates")
public ResponseEntity<Object> getAllCandidates(){
List<Candidate> candidates = candRepo.findAll();

List<JSONObject> entities = new ArrayList<JSONObject>();
for(Candidate candidate: candidates){
    System.out.println(candidate.getParti().getName());
    JSONObject entity = new JSONObject();
    entity.put("id",candidate.getId());
    entity.put("name",candidate.getName());
    entity.put("votes",candidate.getVotes());
    entity.put("parti",candidate.getParti().getName());
    entities.add(entity);
}


return new ResponseEntity<Object>(entities, HttpStatus.OK);

    //return candidates;
}

@GetMapping("/getCandidate/{id}")
public Optional<Candidate> getAllCandidates(@PathVariable Long id){
    return candRepo.findById(id);
}

@GetMapping("/getCandidatesFromParti/{id}")
public Set<Candidate> getCandidatesFromParti(@PathVariable Long id){
    Parti parti = partiesRepo.findById(id).get();
    Set<Candidate> candidates = parti.getCandidates();
    return candidates;
}


@PostMapping("/createCandidate/{partiID}")
public Candidate createCandidate(@RequestBody Candidate candidate, @PathVariable Long partiID){
    Parti parti = partiesRepo.findById(partiID).get();
    return candRepo.save(new Candidate(candidate.getName(),candidate.getVotes(),parti));
}


@PutMapping("/editCandidate/{id}/{partiID}")
public Candidate editCandidate(@PathVariable("id") Long id, @PathVariable("partiID") Long partiID, @RequestBody Candidate newCandidate) {
    if (candRepo.existsById(id)) {
        System.out.println(newCandidate);
        Candidate candidate = candRepo.findById(id).get();
        candidate.setName(newCandidate.getName());
        candidate.setVotes(newCandidate.getVotes());
        Parti parti = partiesRepo.findById(partiID).get();
        candidate.setParti(parti);

        candRepo.save(candidate);
        return candidate;
    }
    return null;
}

@DeleteMapping("/deleteCandidate/{id}")
public void deleteCandidate(@PathVariable Long id){
    try {
        candRepo.deleteById(id);
    } catch (Exception e) {
        e.printStackTrace();
    }
}




}
