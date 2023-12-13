package duck.spring.tutorial.controller;

import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.service.jobcategoties.JobCategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/JobCategory")
@RequiredArgsConstructor
public class JobCategoriesController {

private final JobCategoriesService jobCategoriesService;

    @PostMapping
    public ResponseEntity<JobCategories> createCategory(@RequestBody JobCategories category) {
        JobCategories createdCategory = jobCategoriesService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategories> getCategoryById(@PathVariable Long id) {
        Optional<JobCategories> category = jobCategoriesService.getCategoryById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<JobCategories>> getAllCategories() {
        List<JobCategories> categories = jobCategoriesService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategories> updateCategory(@PathVariable Long id, @RequestBody JobCategories category) {
        JobCategories updatedCategory = jobCategoriesService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        jobCategoriesService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
