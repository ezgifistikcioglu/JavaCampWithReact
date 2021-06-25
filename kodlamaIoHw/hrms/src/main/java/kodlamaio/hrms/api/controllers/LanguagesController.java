package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import kodlamaio.hrms.entities.dtos.LanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
@CrossOrigin
public class LanguagesController {
    private final LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<LanguagesForCv>>> getAll() {
        DataResult<List<LanguagesForCv>> result = languageService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@RequestBody @Valid LanguageDto languageDto) {
        return ResponseEntity.ok(this.languageService.add(languageDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.languageService.delete(id));
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@RequestBody @Valid LanguageDto languageDto) {
        return ResponseEntity.ok(this.languageService.update(languageDto));
    }

    @GetMapping("/findAllByLanguageId")
    public ResponseEntity<DataResult<List<LanguagesForCv>>> findAllByLanguageId(@RequestParam int id) {
        DataResult<List<LanguagesForCv>> result = languageService.findAllByLanguageId(id);
        return ResponseEntity.ok(result);
    }
}
