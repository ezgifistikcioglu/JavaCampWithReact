package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaForCvRepository;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<SocialMediaForCv> socialMediaForCvs = socialMediaForCvRepository.findAllById(id);

        if (socialMediaForCvs.isEmpty()) {
            return new ErrorDataResult<>("This social media address not found");
        } else {
            return new SuccessDataResult<>(socialMediaForCvs, "Social media information has been successfully added");
        }
    }

    @Override
    public DataResult<List<SocialMediaForCv>> getAll() {
        return new SuccessDataResult<>(this.socialMediaForCvRepository.findAll(), "Listed data");
    }

    @Override
    public Result add(SocialMediaForCv socialMedia) {
        if (findAllByCvId(socialMedia.getId()).getData() != null) {
            return new ErrorsResult(socialMedia.getId() + "Same social Media knowledge's cannot repeat");
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
    public Result delete(int id) {
        Optional<SocialMediaForCv> socialMediaForCv = this.socialMediaForCvRepository.findById(id);

        if (!socialMediaForCv.isPresent()) {
            return new ErrorDataResult<>("Social media not found");
        } else {
            this.socialMediaForCvRepository.deleteById(id);
            return new SuccessResult("Deleted socialMedia");
        }
    }
}
