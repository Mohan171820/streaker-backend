package com.example.Streaker.DTO;

import com.example.Streaker.Entity.EffortLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @NotNull(message = "Skill ID is required")
    private Long skillId;

    // Makes sure a date is picked for the practice
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate practiceDate;

    // Checks that the time spent is at least 1 minute or more
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int durationMinutes;

    // Forces the user to select how hard they worked
    @NotNull
    private EffortLevel effortLevel;

    // Optional field where the user can write extra details
    private String notes;
}