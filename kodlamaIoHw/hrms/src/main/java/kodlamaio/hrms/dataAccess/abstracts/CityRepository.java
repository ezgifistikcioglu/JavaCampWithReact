package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCityName(String cityName);
    City getById(int id);
    List<City> findByCityNameContains(String name);
    List<City> findAllById(int id);
}
