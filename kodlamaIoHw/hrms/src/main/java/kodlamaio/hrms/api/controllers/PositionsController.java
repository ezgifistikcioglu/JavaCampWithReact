package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
public class PositionsController {
    private PositionService positionService;

    @Autowired
    public PositionsController(PositionService positionService) {
        super();
        this.positionService = positionService;
    }

    @GetMapping("/getall")
    public DataResult<List<Position>> getAll(){
        return this.positionService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Position position){
        return this.positionService.add(position);
    }

    @GetMapping("/getByJobName")
    public DataResult<Position> getByJobName(@RequestParam String jobName){
        return this.positionService.getByJobName(jobName);
    }
}
