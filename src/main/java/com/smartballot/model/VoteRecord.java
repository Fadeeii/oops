package com.smartballot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "votes")
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private User user;
    @ManyToOne private Candidate candidate;

    public VoteRecord() {}
    public VoteRecord(User user, Candidate candidate) {
        this.user = user;
        this.candidate = candidate;
    }

    // getters and setters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }
}
