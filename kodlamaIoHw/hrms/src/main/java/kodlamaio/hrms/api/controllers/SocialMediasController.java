package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import kodlamaio.hrms.entities.dtos.SocialMediaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/social-medias")
@CrossOrigin
public class SocialMediasController {
    private final SocialMediaService socialMediaService;

    @Autowired
    public SocialMediasController(SocialMediaService socialMediaService) {
        super();
        this.socialMediaService = socialMediaService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<SocialMediaForCv>>> getAll() {
        DataResult<List<SocialMediaForCv>> result = socialMediaService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid SocialMediaDto socialMediaDto) {
        return ResponseEntity.ok(this.socialMediaService.add(socialMediaDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.socialMediaService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid SocialMediaDto socialMediaDto) {
        Result result = socialMediaService.update(socialMediaDto);
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/findAllByCvId/{id}")
    public ResponseEntity<DataResult<List<SocialMediaForCv>>> findAllByCvId(@PathVariable("id") int id) {
        DataResult<List<SocialMediaForCv>> result = socialMediaService.findAllByCvId(id);
        return ResponseEntity.ok(result);
    }
}
