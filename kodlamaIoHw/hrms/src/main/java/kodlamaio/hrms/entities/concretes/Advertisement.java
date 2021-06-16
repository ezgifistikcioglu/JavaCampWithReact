package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "city","position","employer","typeOfWorkFeature","workTimeFeature"})
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

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "number_of_open_position")
    private int numberOfOpenPosition;

    @Column(name = "creation_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_position_id")
    @JsonIgnore
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    @JsonIgnore
    private Employer employer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_type_id")
    @JsonIgnore
    private TypeOfWorkFeature typeOfWorkFeature;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_time_id")
    @JsonIgnore
    private WorkTimeFeature workTimeFeature;
}
