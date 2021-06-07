package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CityRepository;
import kodlamaio.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CityManager implements CityService {

    private CityRepository cityRepository;

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
            return new ErrorDataResult<City>("City not found");
        } else {
            System.out.println("cityResult : " + city.get().toString());
            return new SuccessDataResult<City>(city.get());
        }
    }

    @Override
    public Result addCity(City city) {
        if (findByCityName(city.getCityName()).getData() != null) {
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
        this.cityRepository.deleteById(id);
        return new SuccessResult("Deleted city by id " + id);
    }

    @Override
    public DataResult<List<City>> getAllCity() {
        return new SuccessDataResult<>(this.cityRepository.findAll(), "Listed city data");
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
        return new SuccessDataResult<List<City>>(Objects.requireNonNull(city).getCityName());
    }
}
