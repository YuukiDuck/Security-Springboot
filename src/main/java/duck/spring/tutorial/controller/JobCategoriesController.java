package duck.spring.tutorial.controller;

import duck.spring.tutorial.dto.JobCategoryDto;
import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.repository.JobCategoryRepository;
import duck.spring.tutorial.service.jobcategoties.JobCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1/jobcategory")
@RequiredArgsConstructor
public class JobCategoriesController {
    private final JobCategoriesService jobCategoriesService;
    private final JobCategoryRepository jobCategoryRepository;

    @GetMapping("")
    public List<JobCategories> getAllJobCategories() {
        return jobCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(
            @PathVariable("id") Long categoryId
    ) {
        try {
            JobCategories existingCategory = jobCategoriesService.getCategoryById(categoryId);
            return ResponseEntity.ok(existingCategory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody JobCategoryDto jobCategoryDto,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        jobCategoriesService.createCategory(jobCategoryDto);
        return ResponseEntity.ok().body("Create Category Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody JobCategoryDto jobCategoryDto,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(String.join(", ", errorMessages));
        }
        jobCategoriesService.updateJobCategory(id, jobCategoryDto);
        return ResponseEntity.ok("Update Category with id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            jobCategoriesService.deleteJobCategeory(id);
            return ResponseEntity.ok("Delete Category with id = " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting category with id = " + id);
        }
    }
}