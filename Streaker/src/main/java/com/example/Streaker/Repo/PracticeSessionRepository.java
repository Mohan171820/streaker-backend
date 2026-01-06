package com.example.Streaker.Repo;

import com.example.Streaker.Entity.PracticeSession;
import com.example.Streaker.Entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface PracticeSessionRepository extends JpaRepository<PracticeSession, Long> {
    boolean existsBySkillAndPracticeDate(Skill skill, LocalDate practiceDate);
}
