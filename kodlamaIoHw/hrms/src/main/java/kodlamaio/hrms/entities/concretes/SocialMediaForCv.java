package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "social_medias")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cv"})
public class SocialMediaForCv {
    @Column(name = "id", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "social_media_name")
    private String socialMediaName;

    @Column(name = "social_media_url")
    private String socialMediaUrl;

    @Column(name = "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "cv_id")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Cv.class, cascade = CascadeType.ALL)
    @JsonIgnore
    private Cv cv;
}
