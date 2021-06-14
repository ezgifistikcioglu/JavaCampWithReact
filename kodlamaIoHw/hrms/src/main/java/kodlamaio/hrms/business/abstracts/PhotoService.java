package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    Result add(PhotoInfo photoInfo, MultipartFile imageFile);

    Result update(PhotoInfo resumeImage);

    Result delete(int id);

    DataResult<PhotoInfo> getById(int id);

    DataResult<List<PhotoInfo>> getAll();
}
