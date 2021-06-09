package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.CVService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Cv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cvs")
public class CVsController {
    private CVService cvService;

    @Autowired
    public CVsController(CVService cvService) {
        super();
        this.cvService = cvService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<Cv>>> getAll(){
        DataResult<List<Cv>> result = cvService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid Cv cv){
        return ResponseEntity.ok(this.cvService.add(cv));
    }

    @PostMapping("/delete")
    public ResponseEntity<Result> delete(@RequestBody @Valid Cv cv){
        return ResponseEntity.ok(this.cvService.delete(cv));
    }
    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid Cv cv){
        return ResponseEntity.ok(this.cvService.update(cv));
    }

    @GetMapping("/getByCvId")
    public ResponseEntity<DataResult<Cv>> getByCvId(@RequestParam int id){
        DataResult<Cv> result = cvService.getByCvId(id);
        return ResponseEntity.ok(result);
    }
}
