package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.AdvertisementRepository;
import kodlamaio.hrms.dataAccess.abstracts.EmployerRepository;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementManager implements AdvertisementService {
    private AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementManager(AdvertisementRepository advertisementRepository) {
        super();
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public DataResult<Advertisement> getById(int id) {

        return new SuccessDataResult<Advertisement>(this.advertisementRepository.getOne(id));
    }

    @Override
    public DataResult<Advertisement> findBySalaryMax(double maxSalary) throws NoSuchMethodException {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();

        Advertisement findMaxSalary =  advertisementList.stream().max(Comparator.comparing(Advertisement::getMaxSalary)).orElseThrow(NoSuchMethodException::new);
        return new SuccessDataResult<>(this.advertisementRepository.findByMaxSalary(findMaxSalary.getMaxSalary()), "Max salary");
    }

    @Override
    public DataResult<Advertisement> findBySalaryMin(double minSalary) throws NoSuchMethodException{

        List<Advertisement> advertisementList = this.advertisementRepository.findAll();

        Advertisement findMaxSalary =  advertisementList.stream().max(Comparator.comparing(Advertisement::getMinSalary)).orElseThrow(NoSuchMethodException::new);
        return new SuccessDataResult<Advertisement>(this.advertisementRepository.findByMinSalary(findMaxSalary.getMinSalary()), "Min salary");
    }

    @Override
    public DataResult<Advertisement> findByEmployerId(int id) {

        return new SuccessDataResult<Advertisement>(this.advertisementRepository.findByEmployerId(id));
    }

    @Override
    public Result addAdvertisement(Advertisement advertisement) {
        this.advertisementRepository.save(advertisement);
        return new SuccessResult("Added advertisement");
    }

    @Override
    public Result updateAdvertisement(Advertisement advertisement) {
        this.advertisementRepository.save(advertisement);
        return new SuccessResult("Updated advertisement");
    }

    @Override
    public Result deleteAdvertisement(int id) {
        this.advertisementRepository.deleteById(id);
        return new SuccessResult("Deleted advertisement");
    }

    @Override
    public Result changeOpenToClose(int id) {
        Optional<Advertisement> advertisement = this.advertisementRepository.findById(id);
        String result = "";
        if (advertisement.get().isAdvertisementOpen()){
            advertisement.get().setAdvertisementOpen(false);
            result = "isAdvertisementOpen updated True to False successfully.";
        }else {
            advertisement.get().setAdvertisementOpen(true);
            result = "isAdvertisementOpen updated False to True successfully.";
        }
        this.advertisementRepository.save(advertisement.get());
        return new SuccessResult(result);
    }

    @Override
    public DataResult<List<Advertisement>> getAllAdvertisementList() {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();
        return new SuccessDataResult<>(advertisementList);
    }

    @Override
    public DataResult<List<Advertisement>> getAllOpenAdvertisementList() {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();
        List<Advertisement> openAdvertisementList = advertisementList.stream()
                .filter(Advertisement::isAdvertisementOpen).collect(Collectors.toList());
        return new SuccessDataResult<>(openAdvertisementList);
    }

  //  @Override
  //  public DataResult<List<Advertisement>> findAllByOrderByDateOfPublish() {
  //     List<Advertisement> advertisementList = this.advertisementRepository.findAll();
  //     advertisementList.sort(Comparator.comparing(Advertisement::getDateOfPublish));
  //     return new SuccessDataResult<>(advertisementList);
  //  }
}
