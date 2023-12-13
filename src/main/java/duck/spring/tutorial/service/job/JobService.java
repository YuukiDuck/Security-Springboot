package duck.spring.tutorial.service.job;

import duck.spring.tutorial.model.Jobs;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Jobs createJob(Jobs job);
    Optional<Jobs> getJobById(Long id);
    List<Jobs> getAllJobs();
    Jobs updateJob(Long id, Jobs job);
    void deleteJob(Long id);
}
