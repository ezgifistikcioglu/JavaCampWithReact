package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.MernisVerification;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MernisVerificationRepository extends JpaRepository<MernisVerification,Integer> {
    MernisVerification findByUserId (int userId);
}
