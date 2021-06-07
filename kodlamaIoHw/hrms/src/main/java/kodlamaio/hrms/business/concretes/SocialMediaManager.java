package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaForCvRepository;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialMediaManager implements SocialMediaService {

    private final SocialMediaForCvRepository socialMediaForCvRepository;

    @Autowired
    public SocialMediaManager(SocialMediaForCvRepository socialMediaForCvRepository) {
        super();
        this.socialMediaForCvRepository = socialMediaForCvRepository;
    }

    @Override
    public DataResult<List<SocialMediaForCv>> findAllByCvId(int id) {
        List<SocialMediaForCv> cv = socialMediaForCvRepository.findAllByCvId(id);

        if (cv.isEmpty()) {
            return new ErrorDataResult<List<SocialMediaForCv>>("This social media address Not Found");
        } else {
            return new SuccessDataResult<List<SocialMediaForCv>>(cv);
        }
    }

    @Override
    public DataResult<List<SocialMediaForCv>> getAll() {
        return new SuccessDataResult<List<SocialMediaForCv>>(this.socialMediaForCvRepository.findAll(), "Listed data");
    }

    @Override
    public Result add(SocialMediaForCv socialMedia) {
        if (findAllByCvId(socialMedia.getCvId()).getData() != null) {
            return new ErrorsResult(socialMedia.getCvId() + "Same social Media knowledge's cannot repeat");
        } else {
            this.socialMediaForCvRepository.save(socialMedia);
            return new SuccessResult("Added new socialMedia knowledge");
        }
    }

    @Override
    public Result update(SocialMediaForCv socialMedia) {
        this.socialMediaForCvRepository.save(socialMedia);
        return new SuccessResult("Updated socialMedia");
    }

    @Override
    public Result delete(SocialMediaForCv socialMedia) {
        this.socialMediaForCvRepository.delete(socialMedia);
        return new SuccessResult("Deleted socialMedia");
    }
}
