package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CityRepository;
import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityManager implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityManager(CityRepository cityRepository) {
        super();
        this.cityRepository = cityRepository;
    }

    @Override
    public DataResult<City> getCity(int id) {
        final Optional<City> city = this.cityRepository.findById(id);

        if (!city.isPresent()) {
            System.out.println("cityResult City not found");
            return new ErrorDataResult<>("City not found");
        } else {
            System.out.println("cityResult : " + city.get());
            return new SuccessDataResult<>(city.get());
        }
    }

    @Override
    public Result addCity(City city) {
        if (findAllById(city.getId()).getData() != null) {
            return new ErrorsResult(city.getCityName() + "Same city cannot repeat");
        } else {
            this.cityRepository.save(city);
            return new SuccessResult("Added new city");
        }
    }

    @Override
    public Result updateCity(City city) {
        this.cityRepository.save(city);
        return new SuccessResult("Updated city");
    }

    @Override
    public Result deleteCityById(int id) {
        Optional<City> optionalCity = this.cityRepository.findById(id);

        if (!optionalCity.isPresent()) {
            return new ErrorDataResult<>("JobSeeker not found");
        } else {
            this.cityRepository.deleteById(id);
            return new SuccessResult("Deleted city by id " + id);
        }
    }

    @Override
    public DataResult<List<City>> getAllCity() {
        return new SuccessDataResult<>(this.cityRepository.findAll(), "Listed city data");
    }

    @Override
    public DataResult<List<City>> findAllById(int id) {
        List<City> cities = this.cityRepository.findAllById(id);
        if (cities.isEmpty()){
            return new ErrorDataResult<>("These cities were not found.");
        }else {
            return new SuccessDataResult<>(cities, "Cities have been successfully added");
        }
    }

    @Override
    public DataResult<City> findByCityName(String cityName) {
        return new SuccessDataResult<>(this.cityRepository.findByCityName(cityName), "Added city");
    }

    @Override
    public DataResult<List<City>> findByCityNameContains(String name) {
        List<City> cityList = this.cityRepository.findByCityNameContains(name);
        City city = cityList.stream()
                .filter(cityName -> "James".equals(cityName.getCityName()))
                .findAny()
                .orElse(null);
        return new SuccessDataResult<>(Objects.requireNonNull(city).getCityName());
    }
}
