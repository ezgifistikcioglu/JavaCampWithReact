package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "programming_skills")
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingSkillForCv {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "programming_name")
    private String programmingName;

    @Column(name = "programming_skill_level")
    private int programmingSkillLevel;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "cv_id")
    private int cvId;

    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    @JsonIgnore
    private Cv cv;
}
