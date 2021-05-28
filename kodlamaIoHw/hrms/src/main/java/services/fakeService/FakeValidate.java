package services.fakeService;

import kodlamaio.hrms.entities.concretes.Login;

import java.time.LocalDate;

public class FakeValidate {
    public boolean loginWithoutMernisValidate(String firstName, String lastName, LocalDate dateOfBirth, String nationalityId){
        System.out.println("Signed in without Mernis validation. Welcome : " + firstName + " " + lastName);
        return true;
    }
}
