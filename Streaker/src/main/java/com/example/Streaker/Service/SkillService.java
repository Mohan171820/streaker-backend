package com.example.Streaker.Service;

import com.example.Streaker.DTO.SkillCreateRequest;
import com.example.Streaker.Entity.Skill;
import com.example.Streaker.Entity.User;
import com.example.Streaker.Repo.SkillRepository;
import com.example.Streaker.Repo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createSkill(SkillCreateRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean exists = skillRepository
                .existsByUserIdAndNameIgnoreCaseAndActiveTrue(
                        request.getUserId(),
                        request.getName()
                );

        if (exists) {
            throw new IllegalStateException("Skill already exists for this user");
        }


        if (exists) {
            throw new IllegalStateException("Skill already exists for this user");
        }

        Skill skill = new Skill();
        skill.setId(null); // force INSERT
        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setDecayDays(request.getDecayDays());
        skill.setActive(true);
        skill.setUser(user);

        skillRepository.save(skill);

    }
}
