package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private int educationId;
    private String schoolName;
    private String schoolDepartmentName;
    private LocalDate schoolStartDate;
    private LocalDate schoolGraduationDate;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int cvId;
}
