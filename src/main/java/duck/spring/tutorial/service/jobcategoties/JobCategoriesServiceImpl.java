package duck.spring.tutorial.service.jobcategoties;

import duck.spring.tutorial.dto.JobCategoryDto;
import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobCategoryRepository;
import duck.spring.tutorial.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoriesServiceImpl implements JobCategoriesService {
    private final JobCategoryRepository jobCategoryRepository;
    private final JobRepository jobRepository;

    @Override
    @Transactional
    public JobCategories createCategory(JobCategoryDto jobCategoryDto) {
        JobCategories newjobCategory = JobCategories
                .builder()
                .name(jobCategoryDto.getName())
                .description(jobCategoryDto.getDescription())
                .build();
        return jobCategoryRepository.save(newjobCategory);
    }

    @Override
    public JobCategories getCategoryById(Long id) {
        return jobCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job category not found"));
    }

    @Override
    public List<JobCategories> getAllJobCategories() {
        return jobCategoryRepository.findAll();
    }

    @Override
    @Transactional
    public JobCategories updateJobCategory(Long id, JobCategoryDto jobCategoryDto) {
        JobCategories existingJobCategory = getCategoryById(id);
        existingJobCategory.setName(jobCategoryDto.getName());
        existingJobCategory.setDescription(jobCategoryDto.getDescription());
        jobCategoryRepository.save(existingJobCategory);
        return existingJobCategory;
    }

    @Override
    public JobCategories deleteJobCategeory(Long id) throws Exception {
        JobCategories category = jobCategoryRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<Jobs> jobs = jobRepository.findByCategory(category);
        if (!jobs.isEmpty()) {
            throw new IllegalStateException("Cannot delete job category with associated jobs");
        } else {
            jobCategoryRepository.deleteById(id);
            return category;
        }
    }
}