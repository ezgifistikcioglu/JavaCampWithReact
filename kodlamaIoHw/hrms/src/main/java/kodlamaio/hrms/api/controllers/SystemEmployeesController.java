package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SystemEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/positions")
public class SystemEmployeesController {
    private SystemEmployeeService systemEmployeeService;

    @Autowired
    public SystemEmployeesController(SystemEmployeeService systemEmployeeService) {
        super();
        this.systemEmployeeService = systemEmployeeService;
    }
}
