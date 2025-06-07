package src.main.java.com.example.backend.controller;

import com.example.bugtracker.model.Bug;
import com.example.bugtracker.repository.BugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bugs")
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
        return bugRepository.save(bug);
    }

    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug updatedBug) {
        Optional<Bug> optionalBug = bugRepository.findById(id);
        if (optionalBug.isPresent()) {
            Bug bug = optionalBug.get();
            bug.setTitle(updatedBug.getTitle());
            bug.setDescription(updatedBug.getDescription());
            bug.setResolved(updatedBug.isResolved());
            return bugRepository.save(bug);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable Long id) {
        bugRepository.deleteById(id);
    }
}
