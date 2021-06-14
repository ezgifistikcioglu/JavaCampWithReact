package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "educations")
@AllArgsConstructor
@NoArgsConstructor
public class EducationInformationForCv {
    @Column(name = "education_id")
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

    @JoinColumn(name = "user_id")
    @NotNull
    @ManyToOne()
    @JsonIgnore
    private JobSeeker jobSeeker;

}
