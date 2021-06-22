package kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "employers")
@PrimaryKeyJoinColumn(name = "user_id")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "telephone_no")
    private String telephoneNumber;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_approved", columnDefinition = "boolean default false")
    private boolean isApproved = false;
}
