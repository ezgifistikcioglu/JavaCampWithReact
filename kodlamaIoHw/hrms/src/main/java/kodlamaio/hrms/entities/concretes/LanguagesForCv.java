package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
public class LanguagesForCv {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;

    @Column(name = "language_name")
    private String languageName;

    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "language_level_number")
    private int languageLevelNumber;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    @JsonIgnore
    private Cv cv;

    @Column(name = "cv_id")
    private int cvId;
}
