package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cvs")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
public class Cv {
    @Column(name = "cv_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cvId;

    @Column(name = "photo")
    private String photo;

    @Column(name = "cover_letter")
    private String coverLetter;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private JobSeeker jobSeeker;
}
