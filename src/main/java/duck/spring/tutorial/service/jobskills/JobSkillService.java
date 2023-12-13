package duck.spring.tutorial.service.jobskills;

import duck.spring.tutorial.model.JobSkills;

import java.util.List;
import java.util.Optional;

public interface JobSkillService {
    void deleteJob(Long id);
    JobSkills createSkill(JobSkills skill);
    Optional<JobSkills> getSkillById(Long id);
    List<JobSkills> getAllSkills();
    JobSkills updateSkill(Long id, JobSkills skill);
    void deleteSkill(Long id);
}
