package com.example.bugtracker.controller;

import com.example.bugtracker.model.Bug;
import com.example.bugtracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bugs")
@CrossOrigin(origins = "*")
public class BugController {

    @Autowired
    private BugRepository bugRepository;

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        bug.setStatus("OPEN");
        return bugRepository.save(bug);
    }

    @PutMapping("/{id}/resolve")
    public Bug resolveBug(@PathVariable Long id) {
        Bug bug = bugRepository.findById(id).orElseThrow();
        bug.setStatus("RESOLVED");
        return bugRepository.save(bug);
    }
}