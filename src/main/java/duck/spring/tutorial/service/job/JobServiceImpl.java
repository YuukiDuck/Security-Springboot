package duck.spring.tutorial.service.job;

import duck.spring.tutorial.dto.JobDto;
import duck.spring.tutorial.exception.DataNotFoundException;
import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobCategoryRepository;
import duck.spring.tutorial.repository.JobRepository;
import duck.spring.tutorial.repository.JobSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobCategoryRepository jobCategoryRepository;
    private final JobSkillRepository jobSkillRepository;

    @Override
    @Transactional
    public Jobs createJob(JobDto jobDto) throws DataNotFoundException {
        JobCategories category = jobCategoryRepository
                .findById(jobDto.getCategoryId())
                .orElseThrow(() ->
                        new DataNotFoundException(
                                "Cannot find job category with id:" + jobDto.getCategoryId()
                        ));
        Jobs newJob = Jobs.builder()
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .datePosted(jobDto.getDatePosted())
                .location(jobDto.getLocation())
                .category(category)
                .build();

        return jobRepository.save(newJob);
    }

    @Override
    public Jobs getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Job not found with id:" + id));
    }

    @Override
    public List<Jobs> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public Jobs updateJob(Long id, JobDto jobDto) {
        // Lấy thông tin category từ jobDto
        JobCategories category = jobCategoryRepository.findById(jobDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Cannot find job category with id:" + jobDto.getCategoryId()));

        // Lấy thông tin skills từ jobDto
        Set<JobSkills> skills = new HashSet<>();
        if (jobDto.getSkillIds() != null) {
            skills = jobDto.getSkillIds().stream()
                    .map(skillId -> jobSkillRepository.findById(skillId)
                            .orElseThrow(() -> new RuntimeException("Cannot find skill with id:" + skillId)))
                    .collect(Collectors.toSet());
        }

        // Lấy thông tin existingJob từ id
        Jobs existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        // Cập nhật thông tin của existingJob với các thông tin từ jobDto
        existingJob.setTitle(jobDto.getTitle());
        existingJob.setDescription(jobDto.getDescription());
        existingJob.setDatePosted(jobDto.getDatePosted());
        existingJob.setLocation(jobDto.getLocation());
        existingJob.setCategory(category);
        existingJob.setSkills(skills);

        // Lưu lại và trả về existingJob đã được cập nhật
        return jobRepository.save(existingJob);
    }


    @Override
    @Transactional
    public void deleteJob(Long id) {
        Optional<Jobs> optionalJob = jobRepository.findById(id);
        optionalJob.ifPresent(jobRepository::delete);
    }
}