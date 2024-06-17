package duck.spring.tutorial.service.jobcategoties;

import duck.spring.tutorial.dto.JobCategoryDto;
import duck.spring.tutorial.model.JobCategories;

import java.util.List;

public interface JobCategoriesService {
JobCategories createCategory(JobCategoryDto jobCategoryDto);
JobCategories getCategoryById(Long id);
List<JobCategories> getAllJobCategories();
JobCategories updateJobCategory(Long id, JobCategoryDto jobCategoryDto);
JobCategories deleteJobCategeory(Long id) throws Exception;
}
