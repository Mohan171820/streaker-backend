package com.example.Streaker.Controller;

import com.example.Streaker.DTO.PracticeLogRequest;
import com.example.Streaker.Service.PracticeLoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/practice")
public class PracticeController {

    private final PracticeLoggingService practiceLoggingService;

    public PracticeController(PracticeLoggingService practiceLoggingService) {
        this.practiceLoggingService = practiceLoggingService;
    }

    @PostMapping("/log")
    public ResponseEntity<String> logPractice(
            @RequestBody PracticeLogRequest request) {

        practiceLoggingService.logPractice(request);
        return ResponseEntity.ok("Practice logged successfully");
    }
}
