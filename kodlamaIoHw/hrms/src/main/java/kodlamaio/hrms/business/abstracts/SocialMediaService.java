package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import kodlamaio.hrms.entities.dtos.SocialMediaDto;

import java.util.List;

public interface SocialMediaService {
    DataResult<List<SocialMediaForCv>> findAllByCvId(int id);

    DataResult<List<SocialMediaForCv>> getAll();

    Result add(SocialMediaDto socialMediaDto);

    Result update(SocialMediaDto socialMediaDto);

    Result delete(int id);
}
