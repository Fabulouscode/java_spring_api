package com.example.spring_crud_app.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailTaskPublisher {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enqueueEmailTask(String email, String name) {
        try {
            Map<String, String> emailTask = new HashMap<>();
            emailTask.put("email", email);
            emailTask.put("name", name);

            String jsonTask = objectMapper.writeValueAsString(emailTask);
            redisTemplate.opsForList().rightPush("emailQueue", jsonTask);
        } catch (Exception e) {
            System.err.println("Failed to enqueue email task: " + e.getMessage());
        }
    }
}
