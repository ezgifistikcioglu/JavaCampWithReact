package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.business.abstracts.CityService;
import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.PositionService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.AdvertisementRepository;
import kodlamaio.hrms.entities.concretes.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementManager implements AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final EmployerService employerService;
    private final CityService cityService;
    private final PositionService positionService;

    @Autowired
    public AdvertisementManager(AdvertisementRepository advertisementRepository, EmployerService employerService, CityService cityService, PositionService positionService) {
        super();
        this.advertisementRepository = advertisementRepository;
        this.employerService = employerService;
        this.cityService = cityService;
        this.positionService = positionService;
    }

    private boolean checkMinMaxSalary(double maxSalary, double minSalary) {
        if (minSalary >= maxSalary) {
            System.out.println("Min salary must not be less than max salary, minSalary :  " + minSalary + " Max Salary :" + maxSalary);
            return false;
        }
        return true;
    }

    private boolean isEmployerExists(int id) {
        if (id <= 0 || employerService.getById(id).getData() == null) {
            System.out.println("No such employer found! Employer Id :  " + id);
            return false;
        }
        return true;
    }

    private boolean checkApplicationCreationAndDeadline(LocalDateTime createdDate, LocalDate applicationDeadline) {
        if (!createdDate.isBefore(ChronoLocalDateTime.from(applicationDeadline))) {
            System.out.println("Creation date cannot be later than Application Deadline! ");
            return false;
        }
        return true;
    }

    @Override
    public DataResult<Advertisement> findBySalaryMax(double maxSalary) throws NoSuchMethodException {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();

        Advertisement findMaxSalary = advertisementList.stream().max(Comparator.comparing(Advertisement::getMaxSalary)).orElseThrow(NoSuchMethodException::new);
        return new SuccessDataResult<>(this.advertisementRepository.findByMaxSalary(findMaxSalary.getMaxSalary()), "Max salary");
    }

    @Override
    public DataResult<Advertisement> findBySalaryMin(double minSalary) throws NoSuchMethodException {

        List<Advertisement> advertisementList = this.advertisementRepository.findAll();

        Advertisement findMinSalary = advertisementList.stream().min(Comparator.comparing(Advertisement::getMinSalary)).orElseThrow(NoSuchMethodException::new);
        return new SuccessDataResult<>(this.advertisementRepository.findByMinSalary(findMinSalary.getMinSalary()), "Min salary");
    }

    @Override
    public DataResult<Advertisement> findByEmployerId(int id) {
        return new SuccessDataResult<>(this.advertisementRepository.findByEmployerId(id));
    }

    @Override
    public Result addAdvertisement(Advertisement advertisement) {
        if (findByEmployerId(advertisement.getId()).getData() != null) {
            return new ErrorsResult(advertisement.getId() + "Same advertisement cannot repeat");
        } else {
            if (!isEmployerExists(advertisement.getEmployer().getId())) {
                return new ErrorsResult("No such employer found!");
            }
            if (!checkApplicationCreationAndDeadline(advertisement.getCreatedDate(), advertisement.getApplicationDeadline())) {
                return new ErrorsResult("Creation date cannot be later than Application Deadline");
            }
            if (!checkMinMaxSalary(advertisement.getMaxSalary(), advertisement.getMaxSalary())) {
                return new ErrorsResult("Min salary must not be less than max salary");
            }
            Advertisement addAdvertisement = new Advertisement();
            addAdvertisement.getJobDescription();
            advertisement.getMinSalary();
            addAdvertisement.getMaxSalary();
            addAdvertisement.getApplicationDeadline();
            addAdvertisement.getNumberOfOpenPosition();
            addAdvertisement.getCreatedDate();

            cityService.getCity(advertisement.getCity().getId());
            positionService.findById(advertisement.getPosition().getId());
            employerService.getById(advertisement.getEmployer().getId());

            this.advertisementRepository.save(advertisement);
            return new SuccessResult("Added advertisement");
        }
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
        String result;

        if (!advertisement.isPresent()) {
            result = "advertisement object not available with id : " + id;
        } else {
            if (advertisement.get().isAdvertisementOpen()) {
                advertisement.get().setAdvertisementOpen(false);
                result = "isAdvertisementOpen updated True to False successfully.";
            } else {
                advertisement.get().setAdvertisementOpen(true);
                result = "isAdvertisementOpen updated False to True successfully.";
            }
            this.advertisementRepository.save(advertisement.get());
        }
        return new SuccessResult(result);
    }

    @Override
    public DataResult<List<Advertisement>> getAllAdvertisementList() {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();
        return new SuccessDataResult<>(advertisementList, "Advertisements listed successfully");
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
