package kodlamaio.hrms.entities.concretes;

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
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "min_salary")
    private double minSalary;

    @Column(name = "max_salary")
    private double maxSalary;

    @Column(name = "is_advertisement_open", columnDefinition = "boolean default false")
    private boolean isAdvertisementOpen = false;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private boolean isApproved = false;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "number_of_open_position")
    private int numberOfOpenPosition;

    @Column(name = "creation_date", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "job_position_id")
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeOfWorkFeature.class)
    @JoinColumn(name = "work_type_id")
    private TypeOfWorkFeature typeOfWorkFeature;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = WorkTimeFeature.class)
    @JoinColumn(name = "work_time_id")
    private WorkTimeFeature workTimeFeature;
}