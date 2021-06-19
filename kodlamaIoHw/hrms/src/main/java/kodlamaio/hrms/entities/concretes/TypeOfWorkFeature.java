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
@Table(name = "work_type_features")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisementList"})
public class TypeOfWorkFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_type_id", unique = true, nullable = false)
    private int workTypeId;

    @Column(name = "work_type_name")
    private String workTypeName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "typeOfWorkFeature", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Advertisement> advertisementList;
}
