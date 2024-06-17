package duck.spring.tutorial.repository;

import duck.spring.tutorial.model.JobCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobCategoryRepository extends JpaRepository<JobCategories, Long> {
}
