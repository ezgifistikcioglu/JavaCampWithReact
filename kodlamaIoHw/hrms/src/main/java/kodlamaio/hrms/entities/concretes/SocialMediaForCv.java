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
@Table(name = "social_medias")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cv_id",referencedColumnName = "cv_id")
public class SocialMediaForCv {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "social_media_name")
    private String socialMediaName;

    @NotBlank
    @Column(name = "social_media_url")
    private String socialMediaUrl;

    @NotNull
    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private final LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @JoinColumn(name = "cv_id", insertable = false, updatable = false)
    @ManyToOne()
    private CV cv;

    @Column(name = "cv_id")
    private int cvId;
}
