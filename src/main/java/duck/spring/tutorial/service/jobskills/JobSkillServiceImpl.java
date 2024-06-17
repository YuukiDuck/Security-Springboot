package duck.spring.tutorial.service.jobskills;

import duck.spring.tutorial.dto.JobSkillDto;
import duck.spring.tutorial.model.JobSkills;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    @Override
    public JobSkills createJobSkill(JobSkillDto jobSkillDto) {
        return null;
    }

    @Override
    public JobSkills getJobSkillById(Long id) {
        return null;
    }

    @Override
    public List<JobSkills> getAllJobSkill() {
        return null;
    }

    @Override
    public JobSkills updateJobSkill(Long id, JobSkillDto jobSkillDto) {
        return null;
    }

    @Override
    public JobSkills deleteJobskill(Long id) throws Exception {
        return null;
    }
}
