package duck.spring.tutorial.service.jobcategoties;

import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.repository.JobCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobCategoriesServiceImpl implements JobCategoriesService{
    private final JobCategoryRepository jobCategoryRepository;
    @Override
    public JobCategories createCategory(JobCategories category) {
        return jobCategoryRepository.save(category);
    }

    @Override
    public Optional<JobCategories> getCategoryById(Long id) {
        return jobCategoryRepository.findById(id);
    }

    @Override
    public List<JobCategories> getAllCategories() {
        return jobCategoryRepository.findAll();
    }

    @Override
    public JobCategories updateCategory(Long id, JobCategories category) {
        Optional<JobCategories> existingCategoryOpt = jobCategoryRepository.findById(id);
        if (existingCategoryOpt.isPresent()) {
            JobCategories existingCategory = existingCategoryOpt.get();
            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            return jobCategoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category not found with id " + id);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        jobCategoryRepository.deleteById(id);
    }
}