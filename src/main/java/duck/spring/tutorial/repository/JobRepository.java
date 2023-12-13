package duck.spring.tutorial.repository;

import duck.spring.tutorial.model.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Jobs, Long> {
}
