package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {
    private int experienceId;
    private String businessName;
    private String jobName;
    private LocalDate businessStartDate;
    private LocalDateTime businessLeavingDate = LocalDateTime.now();
    private boolean isStillWorking = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int cvId;
}
