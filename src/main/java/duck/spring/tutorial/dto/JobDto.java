package duck.spring.tutorial.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data//toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private Date datePosted;
    private String location;
    private Long categoryId;
    private Set<Long> skillIds;

}

