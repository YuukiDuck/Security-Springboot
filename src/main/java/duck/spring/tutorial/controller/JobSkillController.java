package duck.spring.tutorial.controller;

import duck.spring.tutorial.dto.JobSkillDto;
import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.repository.JobSkillRepository;
import duck.spring.tutorial.service.jobskills.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobskills")
@RequiredArgsConstructor
public class JobSkillController {
    private final JobSkillService jobSkillService;
    private final JobSkillRepository jobSkillRepository;

    @GetMapping
    public List<JobSkills> getAllJobSkills() {
        return jobSkillRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobSkillById(@PathVariable Long id) {
        try {
            JobSkills existingSkill = jobSkillService.getJobSkillById(id);
            return ResponseEntity.ok(existingSkill);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createJobSkill(
            @Valid @RequestBody JobSkillDto jobSkillDto,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        jobSkillService.createJobSkill(jobSkillDto);
        return ResponseEntity.ok().body("Create Job Skill Succesfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobSkill(
            @PathVariable Long id,
            @Valid @RequestBody JobSkillDto jobSkillDto,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(String.join(", ", errorMessages));
        }
        jobSkillService.updateJobSkill(id, jobSkillDto);
        return ResponseEntity.ok("Update Job Skill with id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobSkill(@PathVariable Long id) {
        try {
            jobSkillService.deleteJobskill(id);
            return ResponseEntity.ok("Delete Job Skill with id = " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting category with id = " + id);
        }
    }
}