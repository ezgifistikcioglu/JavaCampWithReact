package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "advertisements")
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "min_salary")
    private double minSalary;

    @Column(name = "max_salary")
    private double maxSalary;

    @Column(name = "is_advertisement_open", columnDefinition = "boolean default true")
    private boolean isAdvertisementOpen = true;

    @Column(name = "is_advertisement_active", columnDefinition = "boolean default true")
    private boolean isAdvertisementActive = true;

    @Column(name = "is_advertisement_deleted", columnDefinition = "boolean default false")
    private boolean isAdvertisementDeleted = false;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "number_of_open_position")
    private int numberOfOpenPosition;

    @Column(name = "creation_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne()
    @JoinColumn(name = "city_id", insertable = false, updatable = false)
    @JsonIgnore
    private City city;

    @Column(name = "city_id")
    private int cityId;

    @ManyToOne()
    @JoinColumn(name = "job_position_id", insertable = false, updatable = false)
    @JsonIgnore
    private Position position;

    @Column(name = "job_position_id")
    private int jobPositionId;

    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
