package kodlamaio.hrms.services.ImageService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryPhotoUploadAdapter implements CloudinaryPhotoService {
	private Cloudinary cloudinary;

	public CloudinaryPhotoUploadAdapter(){

		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dpgxkyltm",
				"api_key", "817974236589886",
				"api_secret", "-4XbdAImPw73PCOCvqprhpcjof0"));
	}

	@Override
	public DataResult<Map> uploadPhoto(MultipartFile multipartFile) {

		try {
			@SuppressWarnings("unchecked")
			Map<String, String> resultMap =(Map<String, String>) cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
			return new SuccessDataResult<Map>(resultMap);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
