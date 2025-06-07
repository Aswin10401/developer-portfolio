package src.main.java.com.example.backend.repository;

package com.example.bugtracker.repository;

import com.example.bugtracker.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {}
