package com.example.Streaker.DTO;

import com.example.Streaker.Entity.EffortLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeLogRequest {
    @NotNull
    private Long skillId;
    @NotNull
    private LocalDate practiceDate;
    @Min(1)
    private int durationMinutes;
    @NotNull
    private EffortLevel effortLevel;
    private String notes;
}
