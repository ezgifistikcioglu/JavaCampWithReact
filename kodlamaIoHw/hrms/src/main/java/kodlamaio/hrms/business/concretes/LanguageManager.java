package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.LanguagesRepository;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageManager implements LanguageService {

    private final LanguagesRepository languagesRepository;

    @Autowired
    public LanguageManager(LanguagesRepository languagesRepository) {
        super();
        this.languagesRepository = languagesRepository;
    }

    @Override
    public DataResult<List<LanguagesForCv>> findAllByLanguageId(int id) {
        List<LanguagesForCv> languagesForCvs = languagesRepository.findAllByLanguageId(id);

        if (languagesForCvs.isEmpty()) {
            return new ErrorDataResult<>("Information for these languages could not be found.");

        } else {
            return new SuccessDataResult<>(languagesForCvs, "Languages have been successfully added");
        }
    }

    @Override
    public DataResult<LanguagesForCv> getByLanguageId(int id) {
        Optional<LanguagesForCv> languagesForCv = this.languagesRepository.findById(id);

        if (!languagesForCv.isPresent()) {
            return new ErrorDataResult<>("Language not found");
        } else {
            return new SuccessDataResult<>(languagesForCv.get());
        }
    }

    @Override
    public DataResult<List<LanguagesForCv>> getAll() {
        return new SuccessDataResult<>(this.languagesRepository.findAll(), "Listed language data");
    }

    @Override
    public Result add(LanguagesForCv language) {
        if (getByLanguageId(language.getLanguageId()).getData() != null) {
            return new ErrorsResult("id: " + language.getLanguageId() + "Language Name: " + language.getLanguageName() + "Same language cannot repeat");
        } else {
            this.languagesRepository.save(language);
            return new SuccessResult("Added new language");
        }
    }

    @Override
    public Result update(LanguagesForCv language) {
        this.languagesRepository.save(language);
        return new SuccessResult("Updated language");
    }

    @Override
    public Result delete(LanguagesForCv language) {
        this.languagesRepository.delete(language);
        return new SuccessResult("Deleted language");
    }
}
