package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<DataResult<User>> getUser(@PathVariable("id")int id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<DataResult<List<User>>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    @PostMapping("/addUserAccount")
    public ResponseEntity<Result> addUserAccount(@RequestBody User user) {
        final Result result = userService.addUserAccount(user);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping("/updateUserAccount")
    public ResponseEntity<Result> updateUserAccount(@RequestBody User user) {
        return ResponseEntity.ok(this.userService.updateUserAccount(user));
    }

    @DeleteMapping("/deleteUserAccount/{id}")
    public ResponseEntity<Result> deleteUserAccount(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.userService.deleteUserAccount(id));
    }
}
