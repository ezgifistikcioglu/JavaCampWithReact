package kodlamaio.hrms.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;



@Entity
@Data
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "employer_id")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","advertisementList"})
public class Employer extends User {
    @NotBlank
    @NotNull
    @Column(name = "company_name")
    private String companyName;

    @NotBlank
    @NotNull
    @Column(name = "telephone_no")
    private String telephoneNumber;

    @NotBlank
    @NotNull
    @Column(name = "web_address")
    private String webAddress;

    @OneToMany(mappedBy = "employer")
    private List<Advertisement> advertisementList;

}
