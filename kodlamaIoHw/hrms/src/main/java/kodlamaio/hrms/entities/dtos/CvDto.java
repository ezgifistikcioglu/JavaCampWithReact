package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvDto {
    private int cvId;
    private String coverLetter;
    private int userId;
    private int photoId;
    private int languageId;
    private int educationId;
    private int experienceId;
    private int skillId;
    private int socialMediaId;
    private LocalDateTime createdDate;
}
