package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Login;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Login,Integer> {
    void addAccount(User user);
    boolean addVerificationEmail(User user);
    boolean signUp(String email, String password);

    User getUser(int id);
    List<User> getAllUser();
}
