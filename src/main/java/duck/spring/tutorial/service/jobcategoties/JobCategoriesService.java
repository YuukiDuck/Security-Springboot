package duck.spring.tutorial.service.jobcategoties;

import duck.spring.tutorial.model.JobCategories;

import java.util.List;
import java.util.Optional;

public interface JobCategoriesService {
    JobCategories createCategory(JobCategories category);
    Optional<JobCategories> getCategoryById(Long id);
    List<JobCategories> getAllCategories();
    JobCategories updateCategory(Long id, JobCategories category);
    void deleteCategory(Long id);
}
