package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import kodlamaio.hrms.entities.concretes.ProgrammingSkillForCv;
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
    @Autowired
    private LanguageService languageService;

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

    @PutMapping("/update")
    public ResponseEntity<Result> update(@RequestBody LanguageDto languageDto) {
        return ResponseEntity.ok(this.languageService.update(languageDto));
    }

    @GetMapping("/findAllByLanguageId")
    public ResponseEntity<DataResult<List<LanguagesForCv>>> findAllByLanguageId(@RequestParam int id) {
        DataResult<List<LanguagesForCv>> result = languageService.findAllByLanguageId(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/findAllByCvId/{id}")
    public ResponseEntity<DataResult<List<LanguagesForCv>>> findAllByCvId(@PathVariable("id") int id) {
        DataResult<List<LanguagesForCv>> result = languageService.findAllByCvId(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getByCvId/{id}")
    public DataResult<List<LanguagesForCv>> getByCvId(@PathVariable("id") int id) {
        return this.languageService.getByCv_CvId(id);
    }
}
