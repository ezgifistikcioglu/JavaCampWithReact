package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.dtos.AdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@CrossOrigin
public class AdvertisementsController {
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementsController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/addAdvertisement")
    public ResponseEntity<Result> addAdvertisement(@RequestBody AdvertisementDto advertisement) {
        Result result = advertisementService.addAdvertisement(advertisement);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/updateAdvertisement")
    public ResponseEntity<Result> updateAdvertisement(@RequestBody AdvertisementDto advertisement) {
        Result result = advertisementService.addAdvertisement(advertisement);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/deleteAdvertisement")
    public ResponseEntity<Result>  deleteAdvertisement(@RequestParam int id) {
        return ResponseEntity.ok(this.advertisementService.deleteAdvertisement(id));
    }

    @GetMapping("/getAllAdvertisementList")
    public ResponseEntity<DataResult<List<Advertisement>>> getAllAdvertisementList() {
        return ResponseEntity.ok(this.advertisementService.getAllAdvertisementList());
    }

    @GetMapping("/findBySalaryMax")
    public DataResult<Advertisement> findBySalaryMax(@RequestParam double maxSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMax(maxSalary);
    }

    @GetMapping("/findBySalaryMin")
    public DataResult<Advertisement> findBySalaryMin(@RequestParam int minSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMin(minSalary);
    }

    @GetMapping("/findById")
    public DataResult<Advertisement> findById(@RequestParam int id) {
        return this.advertisementService.findById(id);
    }


    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id) {
        return this.advertisementService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenAdvertisementList")
    public DataResult<List<Advertisement>> getAllOpenAdvertisementList() {
        return this.advertisementService.getAllOpenAdvertisementList();
    }
}
