package com.example.Streaker.DTO;

import com.example.Streaker.Entity.EffortLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeLogRequest {
    private Long skillId;
    private LocalDate practiceDate;
    private int durationMinutes;
    private EffortLevel effortLevel;
    private String notes;
}
