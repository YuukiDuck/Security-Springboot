package duck.spring.tutorial.repository;

import duck.spring.tutorial.model.JobCategories;
import duck.spring.tutorial.model.JobSkills;
import duck.spring.tutorial.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Jobs, Long> {
    List<Jobs> findByCategory(JobCategories category);

    List<Jobs> findBySkillsContaining(JobSkills skill);
}
