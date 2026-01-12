package com.example.Streaker.Service;

import com.example.Streaker.DTO.PracticeLogRequest;
import com.example.Streaker.DTO.PracticeResponseDTO;
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

    // Constructor Injection of Repository
    public PracticeLoggingService(PracticeSessionRepository practiceSessionRepository,
            SkillRepository skillRepository) {
        this.practiceSessionRepository = practiceSessionRepository;
        this.skillRepository = skillRepository;
    }

    //Logs a practice session for a given skill.//
    public void logPractice(PracticeLogRequest request) {

        //  Fetch active skill (practice must belong to an active skill)
        Skill skill = skillRepository
                .findByIdAndActiveTrue(request.getSkillId()) // Here the Repo checks the skill with database
                .orElseThrow(() ->
                        new IllegalArgumentException("Skill not found or inactive")); // if the skill not found the give this statement

        //  Validate duration (must be meaningful) like it should be more than 0
        if (request.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero"); // if not it gives this message
        }

        //  Ensure only one practice per skill per day to avoid the fake logging
        boolean alreadyLogged =
                practiceSessionRepository.existsBySkillAndPracticeDate(
                        skill, request.getPracticeDate());

        if (alreadyLogged) {
            throw new IllegalStateException(
                    "Practice already logged for this skill today");
        }

        //  Convert DTO → Entity
        PracticeSession session = new PracticeSession();
        session.setSkill(skill);
        session.setPracticeDate(request.getPracticeDate());
        session.setDurationMinutes(request.getDurationMinutes());
        session.setEffortLevel(request.getEffortLevel());
        session.setNotes(request.getNotes());

        //  Persist only after all validations pass
        practiceSessionRepository.save(session);
    }
}
