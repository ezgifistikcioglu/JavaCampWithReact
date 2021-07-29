package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguagesForCv;
import kodlamaio.hrms.entities.dtos.LanguageDto;

import java.util.List;

public interface LanguageService {
    DataResult<List<LanguagesForCv>> findAllByLanguageId(int id);

    DataResult<List<LanguagesForCv>> findAllByCvId(int id);

    DataResult<LanguagesForCv> getByLanguageId(int id);

    DataResult<List<LanguagesForCv>> getByCv_CvId(int id);

    DataResult<List<LanguagesForCv>> getAll();

    Result add(LanguageDto languageDto);

    Result update(LanguageDto languageDto);

    Result delete(int id);
}
