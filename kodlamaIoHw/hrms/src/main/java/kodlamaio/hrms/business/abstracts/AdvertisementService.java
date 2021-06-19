package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.dtos.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {

    DataResult<Advertisement> findBySalaryMax(double maxSalary) throws NoSuchMethodException;

    DataResult<Advertisement> findBySalaryMin(double minSalary) throws NoSuchMethodException;

    DataResult<Advertisement> findById(int id);

    Result addAdvertisement(AdvertisementDto advertisementDto);

    Result updateAdvertisement(AdvertisementDto advertisementDto);

    Result deleteAdvertisement(int id);

    Result changeOpenToClose(int id);

    DataResult<List<Advertisement>> getAllAdvertisementList();

    DataResult<List<Advertisement>> getAllOpenAdvertisementList();
    // DataResult<List<Advertisement>> findAllByOrderByDateOfPublish();
}
