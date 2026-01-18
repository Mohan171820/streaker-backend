package com.example.Streaker.Controller;

import com.example.Streaker.DTO.SkillCreateRequest;
import com.example.Streaker.Service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    // POST /api/skills
    @PostMapping
    public ResponseEntity<String> addSkill(
            @Valid @RequestBody SkillCreateRequest request) {

        skillService.createSkill(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Skill added successfully");
    }
}
