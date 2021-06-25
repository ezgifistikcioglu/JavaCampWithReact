package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Data
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeeker", "cv"})
public class PhotoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private JobSeeker jobSeeker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cv_id")
    private Cv cv;
}