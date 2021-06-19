package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDto {
    private int id;

    private String jobDescription;

    private double minSalary;

    private double maxSalary;

    private boolean isAdvertisementOpen;

    private int numberOfOpenPosition;

    private LocalDate applicationDeadline;

    private LocalDateTime createdDate;

    private int cityId;

    private int jobPositionId;

    private int employerId;

    private int workTypeId;

    private int workTimeId;
}
