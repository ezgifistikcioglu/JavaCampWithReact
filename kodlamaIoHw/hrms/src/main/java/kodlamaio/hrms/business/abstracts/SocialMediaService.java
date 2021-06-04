package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;

import java.util.List;

public interface SocialMediaService{
	DataResult<List<SocialMediaForCv>> findAllByCvId(int id);
	DataResult<List<SocialMediaForCv>> getAll();

	Result add(SocialMediaForCv socialMedia);
	Result update(SocialMediaForCv socialMedia);
	Result delete(SocialMediaForCv socialMedia);
}
