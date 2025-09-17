package com.smartballot.repo;

import com.smartballot.model.VoteRecord;
import com.smartballot.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteRecord, Long> {
    long countByCandidate(Candidate candidate);
}
