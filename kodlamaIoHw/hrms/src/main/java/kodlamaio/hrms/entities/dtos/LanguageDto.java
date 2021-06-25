package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    private int languageId;
    private String languageName;
    private int languageLevelNumber;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int cvId;
}
