package duck.spring.tutorial.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Job")
public class Jobs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private Date datePosted;

    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategories category;

    @ManyToMany
    @JoinTable(
            name = "job_skill_mappings",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<JobSkills> skills = new HashSet<>();

//    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
//    private List<JobSkills> skills = new ArrayList<>();
}
