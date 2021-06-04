package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisements")
public class AdvertisementController {
    private AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/addAdvertisement")

    public Result addAdvertisement(@RequestBody Advertisement advertisement){
        return this.advertisementService.addAdvertisement(advertisement);
    }

    @GetMapping("/getAllAdvertisementList")
    public DataResult<List<Advertisement>> getAllAdvertisementList(){
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
    public DataResult<Advertisement> findByEmployerId(int id){
        return this.advertisementService.findByEmployerId(id);
    }


    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id){
        return this.advertisementService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenAdvertisementList")
    public DataResult<List<Advertisement>> getAllOpenAdvertisementList(){
        return this.advertisementService.getAllOpenAdvertisementList();
    }

    //@GetMapping("/findAllByOrderByDateOfPublish")
    //public DataResult<List<Advertisement>> findAllByOrderByDateOfPublish(){
    //    return this.advertisementService.findAllByOrderByDateOfPublish();
    //}
}
