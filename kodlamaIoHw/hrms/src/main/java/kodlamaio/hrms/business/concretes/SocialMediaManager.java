package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.SocialMediaService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.dataAccess.abstracts.SocialMediaForCvRepository;
import kodlamaio.hrms.entities.concretes.SocialMediaForCv;
import kodlamaio.hrms.entities.dtos.SocialMediaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SocialMediaManager implements SocialMediaService {

    private final SocialMediaForCvRepository socialMediaForCvRepository;
    private final CVRepository cvRepository;

    @Autowired
    public SocialMediaManager(SocialMediaForCvRepository socialMediaForCvRepository, CVRepository cvRepository) {
        super();
        this.socialMediaForCvRepository = socialMediaForCvRepository;
        this.cvRepository = cvRepository;
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
    public Result add(SocialMediaDto socialMediaDto) {
        if (findAllByCvId(socialMediaDto.getId()).getData() != null) {
            return new ErrorsResult(socialMediaDto.getId() + "Same social Media knowledge's cannot repeat");
        } else {
            SocialMediaForCv media = new SocialMediaForCv();
            media.setSocialMediaName(socialMediaDto.getSocialMediaName());
            media.setSocialMediaUrl(socialMediaDto.getSocialMediaUrl());
            media.setCv(this.cvRepository.getOne(socialMediaDto.getCvId()));
            this.socialMediaForCvRepository.save(media);
            return new SuccessResult("Added new socialMedia knowledge");
        }
    }

    @Override
    public Result update(SocialMediaDto socialMediaDto) {
        Optional<SocialMediaForCv> socialMediaForCv = this.socialMediaForCvRepository.findById(socialMediaDto.getId());
        if (!socialMediaForCv.isPresent()) {
            return new ErrorsResult("This media ( id " + socialMediaDto.getId() + " ) doesnt available!");
        } else {
            socialMediaForCv.get().setSocialMediaName(socialMediaDto.getSocialMediaName());
            socialMediaForCv.get().setSocialMediaUrl(socialMediaDto.getSocialMediaUrl());
            socialMediaForCv.get().setCv(this.cvRepository.getOne(socialMediaDto.getCvId()));
            this.socialMediaForCvRepository.save(socialMediaForCv.get());
            return new SuccessResult("Updated socialMedia");
        }
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
