package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "job_position_name")
    private String jobName;

    @Column(name = "business_start_date")
    private LocalDate businessStartDate;

    @Column(name = "business_leaving_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime businessLeavingDate = LocalDateTime.now();

    @Column(name = "is_still_working", columnDefinition = "boolean default false")
    private boolean isStillWorking = false;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private JobSeeker jobSeeker;

}
