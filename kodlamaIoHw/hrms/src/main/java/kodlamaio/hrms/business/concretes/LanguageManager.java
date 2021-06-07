package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.LanguagesRepository;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {

    private LanguagesRepository languagesRepository;

    @Autowired
    public LanguageManager(LanguagesRepository languagesRepository) {
        super();
        this.languagesRepository = languagesRepository;
    }

    @Override
    public DataResult<List<LanguagesForCv>> findAllByLanguageId(int id) {
        return new SuccessDataResult<List<LanguagesForCv>>(this.languagesRepository.findAllByLanguageId(id), "Added Language");
    }

    @Override
    public DataResult<List<LanguagesForCv>> getAll() {
        return new SuccessDataResult<>(this.languagesRepository.findAll(), "Listed language data");
    }

    @Override
    public Result add(LanguagesForCv language) {
        if (findAllByLanguageId(language.getLanguageId()).getData() != null) {
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
