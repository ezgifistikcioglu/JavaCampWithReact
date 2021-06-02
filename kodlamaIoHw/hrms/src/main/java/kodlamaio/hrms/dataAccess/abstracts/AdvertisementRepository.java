package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    Advertisement findByEmployerId(int id);

    Advertisement findByMaxSalary(double maxSalary);

    Advertisement findByMinSalary(double minSalary);

    @Query("From Advertisement where isAdvertisementOpen = true")
    List<Advertisement> getAllOpenAdvertisementList();

    @Query("From Advertisement where isAdvertisementOpen = false")
    List<Advertisement> getAllCloseAdvertisementList();


    @Query("From Advertisement where isAdvertisementOpen = true Order By dateOfPublish ASC")
    List<Advertisement> findAllByOrderByDateOfPublish();
}
