package duck.spring.tutorial.service.jobskills;

import duck.spring.tutorial.dto.JobSkillDto;
import duck.spring.tutorial.exception.DataNotFoundException;
import duck.spring.tutorial.model.JobSkills;

import java.util.List;

public interface JobSkillService {
    JobSkills createJobSkill(JobSkillDto jobSkillDto);
    JobSkills getJobSkillById(Long id) throws DataNotFoundException;
    List<JobSkills> getAllJobSkill();
    JobSkills updateJobSkill(Long id, JobSkillDto jobSkillDto);
    JobSkills deleteJobskill(Long id) throws Exception;
}

