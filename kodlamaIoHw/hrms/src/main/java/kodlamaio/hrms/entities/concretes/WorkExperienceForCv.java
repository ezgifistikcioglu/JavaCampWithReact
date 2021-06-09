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
@Table(name = "experiences")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class WorkExperienceForCv {
    @Id
    @Column(name = "experience_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int experienceId;

    @NotBlank
    @Column(name = "business_name")
    private String businessName;

    @NotNull
    @Column(name = "business_start_date")
    private LocalDate businessStartDate;

    @Column(name = "business_leaving_date")
    private LocalDate businessLeavingDate;

    @Column(name = "is_still_working", columnDefinition = "Default value false")
    private boolean isStillWorking;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    private Cv cv;

    @NotNull
    @JoinColumn(name = "position_id")
    @ManyToOne
    private Position positionId;

    @Column(name = "cv_id")
    private int cvId;
}
