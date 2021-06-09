package services.ImageService.concretes;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import services.ImageService.abstracts.PhotoService;

import java.io.IOException;
import java.util.Map;

@Service
public class PhotoUploadAdapter implements PhotoService {

	@Override
	public DataResult<Map<String, String>> uploadPhoto(MultipartFile image) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dpgxkyltm", "api_key",
				"817974236589886", "api_secret", "-4XbdAImPw73PCOCvqprhpcjof0"));
		try {
			Map<String, String> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
			return new SuccessDataResult<>(uploadResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ErrorDataResult<>("The photo could not be loaded.");
	}
}
