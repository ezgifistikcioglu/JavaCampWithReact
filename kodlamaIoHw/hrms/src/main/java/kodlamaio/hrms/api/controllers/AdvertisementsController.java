package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementsController {
    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementsController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/addAdvertisement")
    public ResponseEntity<Result> addAdvertisement(@RequestBody Advertisement advertisement) {
        return ResponseEntity.ok(this.advertisementService.addAdvertisement(advertisement));
    }

    @PostMapping("/updateAdvertisement")
    public ResponseEntity<Result> updateAdvertisement(@RequestBody Advertisement advertisement) {
        Result result = advertisementService.addAdvertisement(advertisement);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/deleteAdvertisement")
    public Result deleteAdvertisement(@RequestBody int id) {
        return this.advertisementService.deleteAdvertisement(id);
    }

    @GetMapping("/getAllAdvertisementList")
    public DataResult<List<Advertisement>> getAllAdvertisementList() {
        return this.advertisementService.getAllAdvertisementList();
    }

    @GetMapping("/findBySalaryMax")
    public DataResult<Advertisement> findBySalaryMax(double maxSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMax(maxSalary);
    }

    @GetMapping("/findBySalaryMin")
    public DataResult<Advertisement> findBySalaryMin(int minSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMin(minSalary);
    }

    @GetMapping("/findByEmployerId")
    public DataResult<Advertisement> findByEmployerId(int id) {
        return this.advertisementService.findByEmployerId(id);
    }


    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id) {
        return this.advertisementService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenAdvertisementList")
    public DataResult<List<Advertisement>> getAllOpenAdvertisementList() {
        return this.advertisementService.getAllOpenAdvertisementList();
    }

    //@GetMapping("/findAllByOrderByDateOfPublish")
    //public DataResult<List<Advertisement>> findAllByOrderByDateOfPublish(){
    //    return this.advertisementService.findAllByOrderByDateOfPublish();
    //}
}
