package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.SystemEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/system_employees")
public class SystemEmployeesController {
    private SystemEmployeeService systemEmployeeService;

    @Autowired
    public SystemEmployeesController(SystemEmployeeService systemEmployeeService) {
        super();
        this.systemEmployeeService = systemEmployeeService;
    }

    @GetMapping("/getAll")
    public DataResult<List<SystemEmployee>> getAll(){
        return this.systemEmployeeService.getAllSystemEmployee();
    }
}
