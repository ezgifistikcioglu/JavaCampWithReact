package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Advertisement;

import java.util.List;

public interface AdvertisementService {

    DataResult<Advertisement> getById(int id);
    DataResult<Advertisement> findBySalaryMax(double maxSalary) throws NoSuchMethodException;
    DataResult<Advertisement> findBySalaryMin(double minSalary) throws NoSuchMethodException;
    DataResult<Advertisement> findByEmployerId(int id);

    Result addAdvertisement(Advertisement advertisement);
    Result updateAdvertisement(Advertisement advertisement);
    Result deleteAdvertisement(int id);
    Result changeOpenToClose(int id);

    DataResult<List<Advertisement>> getAllAdvertisementList();
    DataResult<List<Advertisement>> getAllOpenAdvertisementList();
   // DataResult<List<Advertisement>> findAllByOrderByDateOfPublish();
}
