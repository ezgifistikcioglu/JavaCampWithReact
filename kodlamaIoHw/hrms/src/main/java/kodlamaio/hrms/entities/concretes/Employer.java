package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employer_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisementList"})
public class Employer extends User {
    @NotNull
    @Column(name = "company_name")
    private String companyName;

    @NotNull
    @Column(name = "telephone_no")
    private String telephoneNumber;

    @NotNull
    @Column(name = "web_address")
    private String webAddress;

    @OneToMany(mappedBy = "employer")
    @Transient
    private List<Advertisement> advertisementList;

}
