package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForEmployerDto {

    private String email;

    private String companyName;

    private String telephoneNumber;

    private String webAddress;

    private String password;

    private String confirmPassword;
}
