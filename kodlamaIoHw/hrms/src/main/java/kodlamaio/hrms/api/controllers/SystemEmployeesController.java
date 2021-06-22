package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import kodlamaio.hrms.entities.dtos.LoginForEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system_employees")
public class SystemEmployeesController {
    private final SystemEmployeeService systemEmployeeService;

    @Autowired
    public SystemEmployeesController(SystemEmployeeService systemEmployeeService) {
        super();
        this.systemEmployeeService = systemEmployeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<SystemEmployee>>> getAll() {
        final DataResult<List<SystemEmployee>> result = this.systemEmployeeService.getAllSystemEmployee();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Result> addEmployee(@RequestBody LoginForEmployeeDto employeeDto) {
        final Result result = systemEmployeeService.addEmployee(employeeDto);

        if (!result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(result);
    }
}
