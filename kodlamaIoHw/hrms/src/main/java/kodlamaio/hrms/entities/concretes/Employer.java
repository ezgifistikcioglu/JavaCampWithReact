package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employer_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","advertisementList"})
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "telephone_no")
    private String telephoneNumber;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "is_verified")
    private boolean isVerify = false;

    @OneToMany(mappedBy = "employer")
    private List<Advertisement> advertisementList;

}
