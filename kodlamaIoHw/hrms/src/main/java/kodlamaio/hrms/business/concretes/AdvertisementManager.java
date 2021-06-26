package kodlamaio.hrms.business.concretes;

import kodlamaio.hrms.business.abstracts.AdvertisementService;
import kodlamaio.hrms.core.utilities.results.*;
import kodlamaio.hrms.dataAccess.abstracts.*;
import kodlamaio.hrms.entities.concretes.Advertisement;
import kodlamaio.hrms.entities.concretes.User;
import kodlamaio.hrms.entities.dtos.AdvertisementRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AdvertisementManager implements AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final SystemEmployeeRepository systemEmployeeRepository;
    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final CityRepository cityRepository;
    private final PositionRepository positionRepository;
    private final WorkFeatureRepository workFeatureRepository;
    private final WorkTimeRepository workTimeRepository;

    @Autowired
    public AdvertisementManager(AdvertisementRepository advertisementRepository, SystemEmployeeRepository systemEmployeeRepository, UserRepository userRepository, EmployerRepository employerRepository, CityRepository cityRepository, PositionRepository positionRepository, WorkFeatureRepository workFeatureRepository, WorkTimeRepository workTimeRepository) {
        super();
        this.advertisementRepository = advertisementRepository;
        this.systemEmployeeRepository = systemEmployeeRepository;
        this.userRepository = userRepository;
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
        if (id < 0 || !employerRepository.findById(id).isPresent()) {
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

    // TODO : Check valid annotation
    @Override
    public Result addAdvertisement(@Valid AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = new Advertisement();
        if (findById(advertisementRequest.getEmployerId()) == null) {
            return new ErrorsResult(advertisementRequest.getEmployerId() + "Employer cannot be empty!");
        } else {
            System.out.println("addAdvertisement 1: " + advertisementRequest);
            if (!isEmployerExists(advertisementRequest.getEmployerId())) {
                return new ErrorsResult("No such employer found!");
            }
            if (!checkApplicationCreationAndDeadline(advertisementRequest.getCreatedDate(), advertisementRequest.getApplicationDeadline())) {
                return new ErrorsResult("Creation date cannot be later than Application Deadline");
            }
            if (!checkMinMaxSalary(advertisementRequest.getMaxSalary(), advertisementRequest.getMinSalary())) {
                return new ErrorsResult("Min salary must not be less than max salary");
            }
            if (!isOpenPositionControl(advertisementRequest.getNumberOfOpenPosition())) {
                return new ErrorsResult("Open position number cannot be equal to or less than 0.");
            }
            advertisement.setApproved(false);
            advertisement.setAdvertisementOpen(false);

            customField(advertisementRequest, advertisement);

            advertisement.setUser(this.userRepository.getOne(advertisementRequest.getEmployerId()));

            advertisement.setTypeOfWorkFeature(workFeatureRepository.findByWorkTypeId(advertisementRequest.getWorkTypeId()));

            advertisement.setWorkTimeFeature(workTimeRepository.findByWorkTimeId(advertisementRequest.getWorkTimeId()));

            this.advertisementRepository.save(advertisement);
            return new SuccessResult("Added advertisement : " + advertisement);
        }
    }

    @Override
    public Result updateAdvertisement(AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = this.advertisementRepository.findById(advertisementRequest.getId());

        if (!this.userRepository.findById(advertisementRequest.getEmployerId()).isPresent()) {
            return new ErrorsResult("There is not available system employee with this id! : " + advertisementRequest.getEmployerId() + "!");
        } else {
            User user = this.userRepository.findById(advertisementRequest.getEmployerId()).get();
            if (advertisement == null) {
                return new ErrorsResult("This advertisement ( id " + Objects.requireNonNull(advertisement).getId() + " ) doesnt available!");
            } else {
                customField(advertisementRequest, advertisement);
                advertisement.setTypeOfWorkFeature(workFeatureRepository.findByWorkTypeId(advertisementRequest.getWorkTypeId()));
                advertisement.setWorkTimeFeature(workTimeRepository.findByWorkTimeId(advertisementRequest.getWorkTimeId()));

                // print out users
                if (user.isSystemUser()) {
                    //User systemEmpUser = this.systemEmployeeRepository.findByUserId(advertisementRequest.getEmployerId());
                    advertisement.setUser(this.systemEmployeeRepository.findByUserId(advertisementRequest.getEmployerId()));
                    advertisement.setApproved(advertisementRequest.isApproved());
                    advertisement.setAdvertisementOpen(advertisementRequest.isAdvertisementOpen());
                } else {
                    //User employerUser = this.employerRepository.findByUserId(advertisementRequest.getEmployerId());
                    advertisement.setUser(this.employerRepository.findByUserId(advertisementRequest.getEmployerId()));
                    advertisement.setApproved(false);
                    advertisement.setAdvertisementOpen(true);
                }
                this.advertisementRepository.save(advertisement);
                return new SuccessResult("Advertisement (" + advertisement.getId() + ") updated successfully.");
            }
        }
    }

    private void customField(AdvertisementRequest advertisementRequest, Advertisement advertisement) {
        advertisement.setJobDescription(advertisementRequest.getJobDescription());
        advertisement.setMinSalary(advertisementRequest.getMinSalary());
        advertisement.setMaxSalary(advertisementRequest.getMaxSalary());
        advertisement.setNumberOfOpenPosition(advertisementRequest.getNumberOfOpenPosition());
        advertisement.setApplicationDeadline(advertisementRequest.getApplicationDeadline());
        advertisement.setCreatedDate(advertisementRequest.getCreatedDate());
        advertisement.setCity(this.cityRepository.getById(advertisementRequest.getCityId()));
        advertisement.setPosition(this.positionRepository.getById(advertisementRequest.getJobPositionId()));
    }

    @Override
    public Result deleteAdvertisement(int id) {
        List<Advertisement> advertisementList = this.advertisementRepository.findAllById(id);
        if (advertisementList.isEmpty()) {
            return new ErrorDataResult<>("This advertisement not found");
        } else {
            this.advertisementRepository.deleteById(id);
            return new SuccessResult("Deleted advertisement with id : " + id);
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
    public DataResult<List<Advertisement>> getAllOpenAndApprovedAdvertisementList() {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();
        List<Advertisement> advList = advertisementList.stream()
                .filter(advertisement -> advertisement.isApproved() && advertisement.isAdvertisementOpen())
                .collect(Collectors.toList());
        return new SuccessDataResult<>(advList);
    }

    @Override
    public DataResult<List<Advertisement>> getAllWaitApproveAdvertisementList() {
        List<Advertisement> advertisementList = this.advertisementRepository.findAll();
        List<Advertisement> waitApproveAdvList = advertisementList.stream()
                .filter(advertisement -> !advertisement.isApproved()).collect(Collectors.toList());
        return new SuccessDataResult<>(waitApproveAdvList);
    }
}
