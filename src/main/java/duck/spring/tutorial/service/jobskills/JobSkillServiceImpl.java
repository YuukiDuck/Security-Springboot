package duck.spring.tutorial.service.job;

import duck.spring.tutorial.dto.JobSkillDto;
import duck.spring.tutorial.exception.DataNotFoundException;
import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.model.Jobs;
import duck.spring.tutorial.repository.JobRepository;
import duck.spring.tutorial.repository.JobSkillRepository;
import duck.spring.tutorial.service.jobskills.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;
    private final JobRepository jobRepository;

    @Override
    @Transactional
    public JobSkills createJobSkill(JobSkillDto jobSkillDto) {
        JobSkills newJobSkill = JobSkills
                .builder()
                .name(jobSkillDto.getName())
                .description(jobSkillDto.getDescription())
                .build();

        return jobSkillRepository.save(newJobSkill);
    }

    @Override
    public JobSkills getJobSkillById(Long id)  {
        return jobSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job skill not found with id: " + id));
    }

    @Override
    public List<JobSkills> getAllJobSkill() {
        return jobSkillRepository.findAll();
    }

    @Override
    @Transactional
    public JobSkills updateJobSkill(Long id, JobSkillDto jobSkillDto) {
        JobSkills existingJobSkill = getJobSkillById(id);
        existingJobSkill.setName(jobSkillDto.getName());
        existingJobSkill.setDescription(jobSkillDto.getDescription());
        jobSkillRepository.save(existingJobSkill);

        return existingJobSkill;
    }

    @Override
    public JobSkills deleteJobskill(Long id) throws Exception {
        JobSkills skill = jobSkillRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<Jobs> jobs = jobRepository.findBySkillsContaining(skill);
        if (!jobs.isEmpty()) {
            throw new IllegalStateException("Cannot delete job skill with associated jobs");
        } else {
            jobSkillRepository.deleteById(id);
            return skill;
        }
    }
}
