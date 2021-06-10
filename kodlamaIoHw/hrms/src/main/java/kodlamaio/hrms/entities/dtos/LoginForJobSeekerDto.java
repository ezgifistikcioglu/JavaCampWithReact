package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForJobSeekerDto {
    private String firstname;

    private String lastname;

    private String tcNo;

    private int birthYear;

    private String email;

    private String password;

    private String confirmPassword;

}
