package duck.spring.tutorial.service.job;

import duck.spring.tutorial.dto.JobDto;
import duck.spring.tutorial.model.Jobs;

import java.util.List;

public interface JobService {
    Jobs createJob(JobDto jobDto);
    Jobs getJobById(Long id);
    List<Jobs> getAllJob();
    Jobs updateJob(Long id, JobDto jobDto);
    Jobs deleteJob(Long id) throws Exception;
}

