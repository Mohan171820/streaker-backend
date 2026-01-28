package com.example.Streaker.Controller;

import com.example.Streaker.DTO.YoutubeVideoRequest;
import com.example.Streaker.DTO.YoutubeVideoResponse;
import com.example.Streaker.Service.YoutubeVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeVideoController {

    private final YoutubeVideoService videoService;

    public YoutubeVideoController(YoutubeVideoService videoService) {
        this.videoService = videoService;
    }

    // Saves a YouTube video URL provided by the user.
    //This video will be treated as the currently active learning video.
    @PostMapping("/video")
    public ResponseEntity<Void> addVideo(
            @RequestBody YoutubeVideoRequest request) {

        videoService.saveVideo(request.getYoutubeUrl());
        return ResponseEntity.ok().build();
    }

    //  Retrieves the currently active YouTube video that the user is watching or tracking.
    @GetMapping("/current")
    public ResponseEntity<YoutubeVideoResponse> getCurrentVideo() {
        return ResponseEntity.ok(videoService.getCurrentVideo());
    }
}
