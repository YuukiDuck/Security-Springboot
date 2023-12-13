package duck.spring.tutorial.repository;

import duck.spring.tutorial.model.JobSkills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSkillRepository extends JpaRepository<JobSkills, Long> {
}
