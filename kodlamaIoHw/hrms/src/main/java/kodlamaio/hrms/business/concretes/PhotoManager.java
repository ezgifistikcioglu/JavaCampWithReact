package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.PhotoService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerRepository;
import kodlamaio.hrms.dataAccess.abstracts.PhotoRepository;
import kodlamaio.hrms.entities.concretes.PhotoInfo;
import kodlamaio.hrms.services.ImageService.CloudinaryPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PhotoManager implements PhotoService {

    private final PhotoRepository photoRepository;
    private final CloudinaryPhotoService cloudinaryPhotoService;
    private final JobSeekerRepository jobSeekerRepository;

    @Autowired
    public PhotoManager(PhotoRepository photoRepository, CloudinaryPhotoService cloudinaryPhotoService, JobSeekerRepository jobSeekerRepository) {
        super();
        this.photoRepository = photoRepository;
        this.cloudinaryPhotoService = cloudinaryPhotoService;
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public Result uploadPhoto(PhotoInfo photoInfo, MultipartFile imageFile) {
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
        Optional<PhotoInfo> photoInfo = this.photoRepository.findById(id);

        if (photoInfo.isPresent()) {
            return new SuccessDataResult<>(photoInfo.get());
        } else {
            return new ErrorDataResult<>("Photo not found");

        }
    }

    @Override
    public DataResult<List<PhotoInfo>> getAll() {
        return new SuccessDataResult<>(this.photoRepository.findAll());
    }

    @Override
    public DataResult<List<PhotoInfo>> findAllById(int id) {
        List<PhotoInfo> photoInfos = photoRepository.findAllById(id);

        if (photoInfos.isEmpty()) {
            return new ErrorDataResult<>("This Photo Not Found");
        } else {
            return new SuccessDataResult<>(photoInfos);
        }
    }
}