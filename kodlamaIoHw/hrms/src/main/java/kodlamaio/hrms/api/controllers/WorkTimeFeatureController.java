package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.WorkTimeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.City;
import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-times")
@CrossOrigin
public class WorkTimeFeatureController {
    private final WorkTimeService workTimeService;

    @Autowired
    public WorkTimeFeatureController(WorkTimeService workTimeService) {
        super();
        this.workTimeService = workTimeService;
    }

    @PostMapping("/addWorkTime")
    public ResponseEntity<Result> addWorkTime(@RequestBody WorkTimeFeature timeFeature) {
        return ResponseEntity.ok(this.workTimeService.add(timeFeature));
    }

    @GetMapping("/getAllWorkTime")
    public ResponseEntity<DataResult<List<WorkTimeFeature>>> getAllWorkTime() {
        DataResult<List<WorkTimeFeature>> result = workTimeService.getAll();
        return ResponseEntity.ok(result);
    }
}
