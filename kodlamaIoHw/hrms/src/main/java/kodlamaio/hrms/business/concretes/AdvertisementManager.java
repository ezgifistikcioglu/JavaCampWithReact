package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.*;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.*;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.dtos.AdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementManager implements AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final EmployerRepository employerRepository;
    private final CityRepository cityRepository;
    private final PositionRepository positionRepository;
    private final WorkFeatureRepository workFeatureRepository;
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public AdvertisementManager(AdvertisementRepository advertisementRepository, EmployerRepository employerRepository, CityRepository cityRepository, PositionRepository positionRepository, WorkFeatureRepository workFeatureRepository, WorkTimeRepository workTimeRepository) {
        super();
        this.advertisementRepository = advertisementRepository;
        this.employerRepository = employerRepository;
        this.cityRepository = cityRepository;
        this.positionRepository = positionRepository;
        this.workFeatureRepository = workFeatureRepository;
        this.workTimeRepository = workTimeRepository;
    }

    private boolean checkMinMaxSalary(double maxSalary, double minSalary) {
        if (minSalary >= maxSalary) {
            System.out.println("Min salary must not be less than max salary, minSalary :  " + minSalary + " Max Salary :" + maxSalary);
            return false;
        }
        return true;
    }

    private boolean isEmployerExists(int id) {
        if (id < 0 || employerRepository.getByUserId(id) == null) {
            System.out.println("No such employer found! Employer Id :  " + id);
            return false;
        }
        return true;
    }

    private boolean checkApplicationCreationAndDeadline(LocalDateTime createdDate, LocalDate applicationDeadline) {
        System.out.println("createdDate: " + createdDate);
        System.out.println("applicationDeadline: " + applicationDeadline);

        if (!createdDate.isBefore(ChronoLocalDateTime.from(applicationDeadline.atStartOfDay()))) {
            System.out.println("Creation date cannot be later than Application Deadline! ");
            return false;
        }
        return true;
    }

    private boolean isOpenPositionControl(int pos) {
        if (pos == 0 || pos < 0) {
            System.out.println("Open position number cannot be equal to or less than 0. Number of positions you entered : " + pos);
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
    public DataResult<Advertisement> findById(int id) {
        if (!this.advertisementRepository.existsById(id)) {
            return new ErrorDataResult<>("Error! : Advertisement could not be found!");
        }
        return new SuccessDataResult<>(this.advertisementRepository.findById(id));
    }

    @Override
    public Result addAdvertisement(AdvertisementDto advertisementDto) {
        SystemEmployeeManager employeeManager = new SystemEmployeeManager();

        Advertisement advertisement = new Advertisement();
        if (findById(advertisementDto.getEmployerId()) == null) {
            return new ErrorsResult(advertisementDto.getEmployerId() + "Employer cannot be empty!");
        } else {
            System.out.println("addAdvertisement 1: " + advertisementDto);
            if (!isEmployerExists(advertisementDto.getEmployerId())) {
                return new ErrorsResult("No such employer found!");
            }
            if (!checkApplicationCreationAndDeadline(advertisementDto.getCreatedDate(), advertisementDto.getApplicationDeadline())) {
                return new ErrorsResult("Creation date cannot be later than Application Deadline");
            }
            if (!checkMinMaxSalary(advertisementDto.getMaxSalary(), advertisementDto.getMinSalary())) {
                return new ErrorsResult("Min salary must not be less than max salary");
            }
            if (!isOpenPositionControl(advertisementDto.getNumberOfOpenPosition())) {
                return new ErrorsResult("Open position number cannot be equal to or less than 0.");
            }

            // TODO check null states !!!
            advertisement.setJobDescription(advertisementDto.getJobDescription());

            advertisement.setMinSalary(advertisementDto.getMinSalary());

            advertisement.setMaxSalary(advertisementDto.getMaxSalary());

            advertisement.setAdvertisementOpen(false);

            advertisement.setNumberOfOpenPosition(advertisementDto.getNumberOfOpenPosition());

            advertisement.setApplicationDeadline(advertisementDto.getApplicationDeadline());

            advertisement.setCreatedDate(advertisementDto.getCreatedDate());

            advertisement.setCity(this.cityRepository.getById(advertisementDto.getCityId()));

            advertisement.setPosition(this.positionRepository.getById(advertisementDto.getJobPositionId()));

            advertisement.setEmployer(this.employerRepository.getByUserId(advertisementDto.getEmployerId()));

            advertisement.setTypeOfWorkFeature(workFeatureRepository.findByWorkTypeId(advertisementDto.getWorkTypeId()));

            advertisement.setWorkTimeFeature(workTimeRepository.findByWorkTimeId(advertisementDto.getWorkTimeId()));

            if (employeeManager.confirmAdvertisement(advertisement)) {
                this.advertisementRepository.save(advertisement);
                return new SuccessResult("Added advertisement : " + advertisement);
            }
            return new ErrorsResult("Error : You are not approved by system personnel");

        }
    }

    @Override
    public Result updateAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = this.advertisementRepository.findById(advertisementDto.getId());

        //TODO null check
        if (advertisement.equals(false)) {
            return new ErrorsResult("This advertisement ( id " + advertisement.getId() + " ) doesnt available!");
        } else {
            advertisement.setJobDescription(advertisementDto.getJobDescription());
            advertisement.setMinSalary(advertisementDto.getMinSalary());
            advertisement.setMaxSalary(advertisementDto.getMaxSalary());
            advertisement.setAdvertisementOpen(false);
            advertisement.setNumberOfOpenPosition(advertisementDto.getNumberOfOpenPosition());
            advertisement.setApplicationDeadline(advertisementDto.getApplicationDeadline());
            advertisement.setCreatedDate(advertisementDto.getCreatedDate());
            advertisement.setCity(this.cityRepository.getById(advertisementDto.getCityId()));
            advertisement.setPosition(this.positionRepository.getById(advertisementDto.getJobPositionId()));
            advertisement.setEmployer(this.employerRepository.getByUserId(advertisementDto.getEmployerId()));
            advertisement.setTypeOfWorkFeature(workFeatureRepository.findByWorkTypeId(advertisementDto.getWorkTypeId()));
            advertisement.setWorkTimeFeature(workTimeRepository.findByWorkTimeId(advertisementDto.getWorkTimeId()));

            this.advertisementRepository.save(advertisement);
            return new SuccessResult("Advertisement (" + advertisement.getId() + ") updated successfully.");
        }
    }

    @Override
    public Result deleteAdvertisement(int id) {
        List<Advertisement> advertisementList = this.advertisementRepository.findAllById(id);
        if (advertisementList.isEmpty()){
            return new ErrorDataResult<>("This advertisement not found");
        }else {
            this.advertisementRepository.deleteById(id);
            return new SuccessResult("Deleted advertisement with id : " +id);
        }
    }

    @Override
    public Result changeOpenToClose(int id) {
        Advertisement advertisement = this.advertisementRepository.findById(id);
        String result;

        if (advertisement == null) {
            result = "advertisement object not available with id : " + id;
        } else {
            if (advertisement.isAdvertisementOpen()) {
                advertisement.setAdvertisementOpen(false);
                result = "isAdvertisementOpen updated True to False successfully.";
            } else {
                advertisement.setAdvertisementOpen(true);
                result = "isAdvertisementOpen updated False to True successfully.";
            }
            this.advertisementRepository.save(advertisement);
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
}
