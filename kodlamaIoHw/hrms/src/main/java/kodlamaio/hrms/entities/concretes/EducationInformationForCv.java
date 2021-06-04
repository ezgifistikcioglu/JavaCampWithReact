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
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class EducationInformationForCv {
    @Column(name = "education_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;

    @NotBlank
    @Column(name = "school_name")
    private String schoolName;

    @NotBlank
    @Column(name = "school_department_name")
    private String schoolDepartmentName;

    @NotNull
    @Column(name = "school_start_date")
    private LocalDate schoolStartDate;

    @Column(name = "school_graduation_date")
    private LocalDate schoolGraduationDate;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    private CV cv;

    @Column(name = "cv_id")
    private int cvId;
}
