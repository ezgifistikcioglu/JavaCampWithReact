package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.WorkFeatureService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;
import kodlamaio.hrms.entities.concretes.WorkTimeFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-types")
@CrossOrigin
public class TypeOfWorkFeatureController {
    private final WorkFeatureService featureService;

    @Autowired
    public TypeOfWorkFeatureController(WorkFeatureService featureService) {
        super();
        this.featureService = featureService;
    }

    @PostMapping("/addWorkType")
    public ResponseEntity<Result> addWorkType(@RequestBody TypeOfWorkFeature type) {
        return ResponseEntity.ok(this.featureService.add(type));
    }

    @GetMapping("/getAllWorkType")
    public ResponseEntity<DataResult<List<TypeOfWorkFeature>>> getAllWorkTime() {
        DataResult<List<TypeOfWorkFeature>> result = featureService.getAll();
        return ResponseEntity.ok(result);
    }
}
