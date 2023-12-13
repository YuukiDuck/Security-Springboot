package duck.spring.tutorial.service.job;

import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public Jobs createJob(Jobs job) {
        return jobRepository.save(job);
    }

    @Override
    public Optional<Jobs> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Jobs> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Jobs updateJob(Long id, Jobs job) {
        Optional<Jobs> existingJobOpt = jobRepository.findById(id);
        if (existingJobOpt.isPresent()) {
            Jobs existingJob = existingJobOpt.get();
            existingJob.setTitle(job.getTitle());
            existingJob.setDescription(job.getDescription());
            existingJob.setDatePosted(job.getDatePosted());
            existingJob.setLocation(job.getLocation());
            existingJob.setCategory(job.getCategory());
            existingJob.setSkills(job.getSkills());
            existingJob.setActive(job.isActive());
            return jobRepository.save(existingJob);
        } else {
            throw new RuntimeException("Job not found with id " + id);
        }
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
