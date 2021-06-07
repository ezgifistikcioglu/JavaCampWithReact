package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.CVService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.CVRepository;
import kodlamaio.hrms.entities.concretes.CV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CVManager implements CVService {

    private CVRepository cvRepository;

    @Autowired
    public CVManager(CVRepository cvRepository) {
        super();
        this.cvRepository = cvRepository;
    }

    @Override
    public DataResult<List<CV>> getAll() {
        return new SuccessDataResult<List<CV>>(this.cvRepository.findAll(), "Listed data");
    }

    @Override
    public DataResult<CV> getByCvId(int cvId) {
        Optional<CV> cv = cvRepository.getByCvId(cvId);

        if (!cv.isPresent())
            return new ErrorDataResult<CV>("This CV Not Found");

        return new SuccessDataResult<CV>(cv.get());
    }

    @Override
    public Result add(CV cv) {
        if (getByCvId(cv.getCvId()).getData() != null) {
            return new ErrorsResult(cv.getCvId() + "Same cv cannot repeat");
        } else {
            this.cvRepository.save(cv);
            return new SuccessResult("Added new cv");
        }
    }

    @Override
    public Result update(CV cv) {
        this.cvRepository.save(cv);
        return new SuccessResult("Updated cv");
    }

    @Override
    public Result delete(CV cv) {
        this.cvRepository.delete(cv);
        return new SuccessResult("Deleted cv");
    }
}
