package duck.spring.tutorial.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePosted;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private JobCategories category;

    @ManyToMany
    @JoinTable(
            name = "job_skill_mappings",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<JobSkills> skills = new HashSet<>();

    @Column(nullable = false)
    private boolean isActive;
}
