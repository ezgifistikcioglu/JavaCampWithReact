package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "work_time_features")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisementList"})
public class WorkTimeFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_time_id")
    private int workTimeId;

    @Column(name = "work_time_name")
    private String workTimeName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workTimeFeature", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Advertisement> advertisementList;
}
