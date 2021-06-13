package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "job_positions")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisementList"})
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "is_active_position", columnDefinition = "Default value true")
    private boolean isActivePosition = true;

    @Column(name = "is_deleted_position", columnDefinition = "Default value false")
    private boolean isDeletedPosition = false;

    @Column(name = "created_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @OneToMany(mappedBy = "position")
    @Transient
    @JsonIgnore
    private List<Advertisement> advertisementList;

    @OneToMany(mappedBy = "position")
    @Transient
    @JsonIgnore
    private List<WorkExperienceForCv> workExperienceForCvs;
}
