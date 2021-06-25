package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.LanguageService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.LanguagesRepository;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import kodlamaio.hrms.entities.dtos.LanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageManager implements LanguageService {

    private final LanguagesRepository languagesRepository;
    private final CVRepository cvRepository;

    @Autowired
    public LanguageManager(LanguagesRepository languagesRepository, CVRepository cvRepository) {
        super();
        this.languagesRepository = languagesRepository;
        this.cvRepository = cvRepository;
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
    public Result add(LanguageDto languageDto) {
        if (getByLanguageId(languageDto.getLanguageId()).getData() != null) {
            return new ErrorsResult("id: " + languageDto.getLanguageId() + "Language Name: " + languageDto.getLanguageName() + "Same language cannot repeat");
        } else {
            LanguagesForCv language = new LanguagesForCv();
            language.setLanguageName(languageDto.getLanguageName());
            language.setLanguageLevelNumber(languageDto.getLanguageLevelNumber());
            language.setCreatedAt(languageDto.getCreatedAt());
            language.setCv(this.cvRepository.getOne(languageDto.getCvId()));
            this.languagesRepository.save(language);
            return new SuccessResult("Added new language");
        }
    }

    @Override
    public Result update(LanguageDto languageDto) {
        Optional<LanguagesForCv> language = this.languagesRepository.getByLanguageId(languageDto.getLanguageId());
        if (!language.isPresent()) {
            return new ErrorsResult("This language ( id " + languageDto.getLanguageId() + " ) doesnt available!");
        } else {
            language.get().setLanguageName(languageDto.getLanguageName());
            language.get().setLanguageLevelNumber(languageDto.getLanguageLevelNumber());
            language.get().setCreatedAt(languageDto.getCreatedAt());
            language.get().setCv(this.cvRepository.getOne(languageDto.getCvId()));
            this.languagesRepository.save(language.get());
            return new SuccessResult("Updated language");
        }
    }

    @Override
    public Result delete(int id) {
        List<LanguagesForCv> languagesForCvs = this.languagesRepository.findAllByLanguageId(id);

        if (languagesForCvs.isEmpty()) {
            return new ErrorDataResult<>("This language not found");
        } else {
            this.languagesRepository.deleteById(id);
            return new SuccessResult("Deleted language with id :" + id);
        }
    }
}
