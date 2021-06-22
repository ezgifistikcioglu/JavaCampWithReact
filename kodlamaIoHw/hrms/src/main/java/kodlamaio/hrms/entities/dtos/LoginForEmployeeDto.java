package kodlamaio.hrms.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForEmployeeDto {
    private int id;

    private String email;

    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private boolean isApproved;
}
