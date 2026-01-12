package com.example.Streaker.DTO;

import com.example.Streaker.Entity.EffortLevel;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record PracticeResponseDTO(
        Long skillId,
        String skillName,
        LocalDate practiceDate,
        int durationMinutes,
        EffortLevel effortLevel){

}
