package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.City;

import java.util.List;

public interface CityService {

    Result addCity(City city);

    Result updateCity(City city);

    Result deleteCityById(int id);

    DataResult<City> getCity(int id);

    DataResult<List<City>> getAllCity();

    DataResult<City> findByCityName(String cityName);

    DataResult<List<City>> findByCityNameContains(String name);


}
