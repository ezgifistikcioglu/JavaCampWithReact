package kodlamaio.hrms.core.adapters.concretes;

public class LoginWithEmail {
    public boolean loginWithEmail(String email){
        System.out.println("Signed in with Email Authentication. " + email);
        return true;
    }
}
