package duck.spring.tutorial.service.jobskills;

import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.repository.JobSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    @Override
    public Optional<JobSkills> getSkillById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<JobSkills> getAllSkills() {
        return null;
    }

    @Override
    public void deleteJob(Long id) {

    }

    @Override
    public JobSkills createSkill(JobSkills skill) {
        return null;
    }


    @Override
    public JobSkills updateSkill(Long id, JobSkills skill) {
        return null;
    }

    @Override
    public void deleteSkill(Long id) {

    }
}
