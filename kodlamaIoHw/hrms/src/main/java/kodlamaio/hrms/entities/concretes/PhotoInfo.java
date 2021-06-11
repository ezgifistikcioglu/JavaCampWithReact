package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "photos")
@NoArgsConstructor
@AllArgsConstructor
public class PhotoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_seeker_id", insertable = false, updatable = false)
    private int jobSeekerId;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToOne()
    @JsonIgnore
    @JoinColumn(name = "job_seeker_id", referencedColumnName = "job_seeker_id")
    private JobSeeker jobSeeker;

}