package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgrammingSkillDto {
    private int skillId;
    private String programmingName;
    private int programmingSkillLevel;
    private LocalDateTime createdAt = LocalDateTime.now();
    private int cvId;
}
