package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.PhotoRepository;
import kodlamaio.hrms.entities.concretes.PhotoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import kodlamaio.hrms.services.ImageService.CloudinaryPhotoService;

import java.util.List;
import java.util.Map;

@Service
public class PhotoManager implements PhotoService {

    private final PhotoRepository photoRepository;
    private final CloudinaryPhotoService cloudinaryPhotoService;

    @Autowired
    public PhotoManager(PhotoRepository photoRepository, CloudinaryPhotoService cloudinaryPhotoService) {
        super();
        this.photoRepository = photoRepository;
        this.cloudinaryPhotoService = cloudinaryPhotoService;
    }

    @Override
    public Result add(PhotoInfo photoInfo, MultipartFile imageFile) {
        @SuppressWarnings("unchecked")
        Map<String, String> uploadImage = this.cloudinaryPhotoService.uploadPhoto(imageFile).getData();
        photoInfo.setPhotoUrl(uploadImage.get("url"));
        this.photoRepository.save(photoInfo);
        return new SuccessResult("Your Photo Has Been Successfully Added To The System");
    }

    @Override
    public Result update(PhotoInfo photoInfo) {
        this.photoRepository.save(photoInfo);
        return new SuccessResult("Your Photo Has Been Successfully Updated");
    }

    @Override
    public Result delete(int id) {
        this.photoRepository.deleteById(id);
        return new SuccessResult("Your Photo Has Been Deleted");
    }

    @Override
    public DataResult<PhotoInfo> getById(int id) {
        return new SuccessDataResult<>(this.photoRepository.getById(id));
    }

    @Override
    public DataResult<List<PhotoInfo>> getAll() {
        return new SuccessDataResult<>(this.photoRepository.findAll());
    }
}