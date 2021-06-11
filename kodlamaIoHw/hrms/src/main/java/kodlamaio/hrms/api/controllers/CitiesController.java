package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        super();
        this.cityService = cityService;
    }

    @GetMapping("/getCity")
    public ResponseEntity<DataResult<City>> getCity(int id) {
        DataResult<City> result = cityService.getCity(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllCity")
    public ResponseEntity<DataResult<List<City>>> getAllCity() {
        DataResult<List<City>> result = cityService.getAllCity();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addCity")
    public ResponseEntity<Result> addCity(@RequestBody City city) {
        return ResponseEntity.ok(this.cityService.addCity(city));
    }

    @PostMapping("/updateCity")
    public ResponseEntity<Result> updateCity(@RequestBody City city) {
        return ResponseEntity.ok(this.cityService.updateCity(city));
    }

    @PostMapping("/deleteCityById")
    public ResponseEntity<Result> deleteCityById(@RequestBody int id) {
        return ResponseEntity.ok(this.cityService.deleteCityById(id));
    }

    @PostMapping("/findByCityName")
    public ResponseEntity<Result> findByCityName(@RequestBody String cityName) {
        return ResponseEntity.ok(this.cityService.findByCityName(cityName));
    }
}
