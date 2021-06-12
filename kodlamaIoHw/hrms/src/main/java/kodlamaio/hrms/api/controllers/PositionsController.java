package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@CrossOrigin
public class PositionsController {
    private final PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        super();
        this.positionService = positionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<DataResult<List<Position>>> getAll() {
        return ResponseEntity.ok(this.positionService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody Position position) {
        return ResponseEntity.ok(this.positionService.add(position));
    }

    @GetMapping("/getByJobName")
    public ResponseEntity<DataResult<Position>> getByJobName(@RequestParam String jobName) {
        return ResponseEntity.ok(this.positionService.getByJobName(jobName));
    }
}
