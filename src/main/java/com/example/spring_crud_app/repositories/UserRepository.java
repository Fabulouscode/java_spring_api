package com.example.spring_crud_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_crud_app.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
