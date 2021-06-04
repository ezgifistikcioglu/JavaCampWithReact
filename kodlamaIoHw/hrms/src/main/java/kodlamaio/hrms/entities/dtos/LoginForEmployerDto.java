package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForEmployerDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String companyName;

    @NotBlank
    private String telephoneNumber;

    @NotBlank
    private String webAddress;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
