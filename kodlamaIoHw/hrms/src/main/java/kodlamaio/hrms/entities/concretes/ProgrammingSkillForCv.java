package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "programming_skills")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class ProgrammingSkillForCv {

    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cv_id")
    private int cvId;

    @NotNull
    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    private CV cv;

    @NotBlank
    @Column(name = "programming_name")
    private String programmingName;

    @NotBlank
    @Column(name="programming_skill_level")
    private int programmingSkillLevel;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
