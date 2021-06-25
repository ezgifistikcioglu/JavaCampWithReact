package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialMediaDto {
    private int id;
    private String socialMediaName;
    private String socialMediaUrl;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int cvId;
}
