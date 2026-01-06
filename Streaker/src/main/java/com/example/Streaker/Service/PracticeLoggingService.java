package com.example.Streaker.Service;

import com.example.Streaker.DTO.PracticeLogRequest;
import com.example.Streaker.Entity.PracticeSession;
import com.example.Streaker.Entity.Skill;
import com.example.Streaker.Repo.PracticeSessionRepository;
import com.example.Streaker.Repo.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeLoggingService {

    private final PracticeSessionRepository practiceSessionRepository;
    private final SkillRepository skillRepository;

    // Constructor Injection
    public PracticeLoggingService(
            PracticeSessionRepository practiceSessionRepository,
            SkillRepository skillRepository) {
        this.practiceSessionRepository = practiceSessionRepository;
        this.skillRepository = skillRepository;
    }

    /**
     * Logs a practice session for a given skill.
     * Enforces all business rules before saving.
     */
    public void logPractice(PracticeLogRequest request) {

        // 1. Fetch active skill (practice must belong to an active skill)
        Skill skill = skillRepository
                .findByIdAndActiveTrue(request.getSkillId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Skill not found or inactive"));

        // 2. Validate duration (must be meaningful)
        if (request.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }

        // 3. Ensure only one practice per skill per day
        boolean alreadyLogged =
                practiceSessionRepository.existsBySkillAndPracticeDate(
                        skill, request.getPracticeDate());

        if (alreadyLogged) {
            throw new IllegalStateException(
                    "Practice already logged for this skill today");
        }

        // 4. Convert DTO → Entity
        PracticeSession session = new PracticeSession();
        session.setSkill(skill);
        session.setPracticeDate(request.getPracticeDate());
        session.setDurationMinutes(request.getDurationMinutes());
        session.setEffortLevel(request.getEffortLevel());
        session.setNotes(request.getNotes());

        // 5. Persist only after all validations pass
        practiceSessionRepository.save(session);
    }
}
