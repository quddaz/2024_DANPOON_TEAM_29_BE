package com.globalnest.be.petition.repository;

import com.globalnest.be.petition.domain.Petition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetitionRepository extends JpaRepository<Petition, Long> {
}
