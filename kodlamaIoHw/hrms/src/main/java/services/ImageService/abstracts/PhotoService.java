package services.ImageService.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
	DataResult<?> uploadPhoto(MultipartFile image);
}
