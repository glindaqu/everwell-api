package ru.glindaquint.everwell.controllers;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.glindaquint.everwell.services.EmailService;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private HttpServletRequest request;

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, this::createNewBucket);
    }

    private Bucket createNewBucket(String key) {
        return Bucket.builder()
                .addLimit(limit -> limit.capacity(1).refillGreedy(1, Duration.ofMinutes(3)))
                .build();
    }

    @GetMapping("/send-email")
    public ResponseEntity<?> sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body
    ) {
        Bucket bucket = resolveBucket(request.getRemoteAddr());
        if (bucket.tryConsume(1)) {
            emailService.send(to, subject, body);
            return ResponseEntity.ok("Sent");
        } else {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Request limit reached");
        }
    }
}