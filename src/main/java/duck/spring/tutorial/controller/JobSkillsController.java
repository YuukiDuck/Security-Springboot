package duck.spring.tutorial.controller;

import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.service.jobskills.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class JobSkillsController {

private final JobSkillService jobSkillService;

    @PostMapping
    public ResponseEntity<JobSkills> createSkill(@RequestBody JobSkills skill) {
        JobSkills createdSkill = jobSkillService.createSkill(skill);
        return ResponseEntity.ok(createdSkill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkills> getSkillById(@PathVariable Long id) {
        Optional<JobSkills> skill = jobSkillService.getSkillById(id);
        return skill.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JobSkills>> getAllSkills() {
        List<JobSkills> skills = jobSkillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkills> updateSkill(@PathVariable Long id, @RequestBody JobSkills skill) {
        JobSkills updatedSkill = jobSkillService.updateSkill(id, skill);
        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }
}
