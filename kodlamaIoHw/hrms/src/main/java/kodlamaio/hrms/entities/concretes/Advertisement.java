package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

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

    @Column(name = "is_advertisement_open")
    private boolean isAdvertisementOpen;

    @Column(name = "is_advertisement_active")
    private boolean isAdvertisementActive;

    @Column(name = "is_advertisement_deleted")
    private boolean isAdvertisementDeleted;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "number_of_open_position")
    private int numberOfOpenPosition;

    @Column(name = "creation_date")
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "job_position_id")
    private Position position;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
}
