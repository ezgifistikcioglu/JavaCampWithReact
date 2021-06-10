package kodlamaio.hrms.services.ImageService;

import kodlamaio.hrms.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryPhotoService {
	DataResult<Map> uploadPhoto(MultipartFile multipartFile);
}
