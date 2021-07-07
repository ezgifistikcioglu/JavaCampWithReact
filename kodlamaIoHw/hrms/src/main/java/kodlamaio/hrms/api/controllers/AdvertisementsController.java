package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.dtos.AdvertisementRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
@CrossOrigin
public class AdvertisementsController {
    @Autowired
    private  AdvertisementService advertisementService;

    @PostMapping("/addAdvertisement")
    public ResponseEntity<Result> addAdvertisement(@RequestBody AdvertisementRequest advertisement) {
        Result result = advertisementService.addAdvertisement(advertisement);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/updateAdvertisement")
    public ResponseEntity<Result> updateAdvertisement(@RequestBody AdvertisementRequest advertisement) {
        Result result = advertisementService.updateAdvertisement(advertisement);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/deleteAdvertisement/{id}")
    public ResponseEntity<Result>  deleteAdvertisement(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.advertisementService.deleteAdvertisement(id));
    }

    @GetMapping("/getAllAdvertisementList")
    public ResponseEntity<DataResult<List<Advertisement>>> getAllAdvertisementList() {
        return ResponseEntity.ok(this.advertisementService.getAllAdvertisementList());
    }

    @GetMapping("/findBySalaryMax/{maxSalary}")
    public DataResult<Advertisement> findBySalaryMax(@PathVariable("maxSalary") double maxSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMax(maxSalary);
    }

    @GetMapping("/findBySalaryMin/{minSalary}")
    public DataResult<Advertisement> findBySalaryMin(@PathVariable("minSalary") int minSalary) throws NoSuchMethodException {
        return this.advertisementService.findBySalaryMin(minSalary);
    }

    @GetMapping("/findById/{id}")
    public DataResult<Advertisement> findById(@PathVariable("id") int id) {
        return this.advertisementService.findById(id);
    }


    @PostMapping("/changeOpenToClose/{id}")
    public Result changeOpenToClose(@PathVariable("id") int id) {
        return this.advertisementService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenAndApprovedAdvertisementList")
    public DataResult<List<Advertisement>> getAllOpenAndApprovedAdvertisementList() {
        return this.advertisementService.getAllOpenAndApprovedAdvertisementList();
    }
    @GetMapping("/getAllWaitApproveAdvertisementList")
    public DataResult<List<Advertisement>> getAllWaitApproveAdvertisementList() {
        return this.advertisementService.getAllWaitApproveAdvertisementList();
    }
}
