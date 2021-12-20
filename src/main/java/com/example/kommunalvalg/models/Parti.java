package com.example.kommunalvalg.models;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="parties")
public class Parti {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String name;
    private int votes;

    public Parti(String name, int votes) {
        this.name = name;
        this.votes = votes;
    }

    @OneToMany(mappedBy = "parti", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Candidate> candidates;

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.candidates = candidates;
    }

    public Parti() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
