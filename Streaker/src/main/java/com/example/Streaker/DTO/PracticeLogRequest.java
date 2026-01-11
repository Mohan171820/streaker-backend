package com.example.Streaker.DTO;

import com.example.Streaker.Entity.EffortLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// This class acts as a container to move data from the website to our code
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeLogRequest {

    // Ensures the skill ID is provided and not empty
    @NotNull
    private Long skillId;

    // Makes sure a date is picked for the practice
    @NotNull
    private LocalDate practiceDate;

    // Checks that the time spent is at least 1 minute or more
    @Min(1)
    private int durationMinutes;

    // Forces the user to select how hard they worked
    @NotNull
    private EffortLevel effortLevel;

    // Optional field where the user can write extra details
    private String notes;
}