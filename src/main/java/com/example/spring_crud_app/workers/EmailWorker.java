package com.example.spring_crud_app.workers;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.spring_crud_app.services.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailWorker {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 10000) // Runs every 10 seconds
    public void listenForEmailTasks() {
        String emailTaskJson;
        while ((emailTaskJson = redisTemplate.opsForList().leftPop("emailQueue")) != null) {
            try {
                Map<String, String> emailTask = objectMapper.readValue(emailTaskJson,
                        new TypeReference<Map<String, String>>() {
                        });

                String email = emailTask.get("email");
                String name = emailTask.get("name");

                emailService.sendWelcomeEmail(email, name);
            } catch (Exception e) {
                System.err.println("Failed to process email task: " + e.getMessage());
            }
        }
    }
}
