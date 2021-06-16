package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisementList"})
public class Employer extends User {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "telephone_no")
    private String telephoneNumber;

    @Column(name = "web_address")
    private String webAddress;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Advertisement> advertisementList;
}
