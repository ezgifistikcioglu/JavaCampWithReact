package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "experiences")
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceForCv {
    @Id
    @Column(name = "experience_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int experienceId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "business_start_date")
    private LocalDate businessStartDate;

    @Column(name = "business_leaving_date")
    private LocalDate businessLeavingDate;

    @Column(name = "is_still_working", columnDefinition = "Default value false")
    private boolean isStillWorking;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @JsonIgnore
    @ManyToOne()
    private Cv cv;

    @JoinColumn(name = "position_id", insertable = false, updatable = false)
    @JsonIgnore
    @ManyToOne
    private Position position;

    @Column(name = "cv_id")
    private int cvId;

    @Column(name = "position_id")
    private int positionId;
}
