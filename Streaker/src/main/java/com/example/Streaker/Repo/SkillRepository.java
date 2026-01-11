package com.example.Streaker.Repo;

import com.example.Streaker.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
 // Here this repository checks the skill with the database
    Optional<Skill> findByIdAndActiveTrue(Long id);
}
