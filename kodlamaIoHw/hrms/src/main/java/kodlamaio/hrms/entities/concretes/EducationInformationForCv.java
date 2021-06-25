package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cv"})
public class EducationInformationForCv {
    @Column(name = "education_id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int educationId;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_department_name")
    private String schoolDepartmentName;

    @Column(name = "school_start_date")
    private LocalDate schoolStartDate;

    @Column(name = "school_graduation_date")
    private LocalDate schoolGraduationDate;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Cv.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Cv cv;

}
