package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class LanguagesForCv {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageId;

    @NotBlank
    @Column(name = "language_name")
    private String languageName;

    @NotBlank
    @Column(name = "language_level_number")
    private int languageLevelNumber;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    private Cv cv;

    @Column(name = "cv_id")
    private int cvId;
}
