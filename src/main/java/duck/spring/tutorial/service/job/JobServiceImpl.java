package duck.spring.tutorial.service.job;

import duck.spring.tutorial.dto.JobDto;
import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Override
    public Jobs createJob(JobDto jobDto) {
        return null;
    }

    @Override
    public Jobs getJobById(Long id) {
        return null;
    }

    @Override
    public List<Jobs> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public Jobs updateJob(Long id, JobDto jobDto) {
        return null;
    }

    @Override
    public Jobs deleteJob(Long id) throws Exception {
        return null;
    }
}