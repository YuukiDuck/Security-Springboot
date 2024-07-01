package duck.spring.tutorial.controller;

import duck.spring.tutorial.dto.JobDto;
import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobRepository;
import duck.spring.tutorial.service.job.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;
    private final JobRepository jobRepository;

    @GetMapping
    public List<Jobs> getAllJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(
            @PathVariable("id") Long id
    ){
        try {
            Jobs existingJob= jobService.getJobById(id);
            return ResponseEntity.ok(existingJob);
        } catch ( Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<?> createJob (
            @Valid @RequestBody JobDto jobDto,
            BindingResult result
            ){
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        jobService.createJob(jobDto);
        return ResponseEntity.ok().body("Create Category Successfully");
    }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateJob(
          @PathVariable Long id,
          @Valid @RequestBody JobDto jobDto,
          BindingResult result) {
      if (result.hasErrors()) {
          List<String> errorMessages = result.getFieldErrors()
                  .stream()
                  .map(FieldError::getDefaultMessage)
                  .toList();
          return ResponseEntity.badRequest().body(String.join(", ", errorMessages));
      }
      jobService.updateJob(id, jobDto);
      return ResponseEntity.ok("Update Category with id = " + id);
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        try {
            jobService.deleteJob(id);
            return ResponseEntity.ok("Delete Category with id = " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting category with id = " + id);
        }
    }
}