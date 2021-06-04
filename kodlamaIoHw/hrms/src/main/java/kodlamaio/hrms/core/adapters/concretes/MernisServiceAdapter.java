package kodlamaio.hrms.core.adapters.concretes;

import kodlamaio.hrms.core.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.entities.dtos.LoginForJobSeekerDto;
import org.springframework.stereotype.Service;
import services.mernisService.GSPKPSPublicSoap;

@Service
public class MernisServiceAdapter implements MernisCheckService {

    @Override
    public boolean checkIfRealPerson(LoginForJobSeekerDto jobSeeker) {
        GSPKPSPublicSoap publicSoap = new GSPKPSPublicSoap();
        try {
            return publicSoap.TCKimlikNoDogrula(
                    Long.parseLong(jobSeeker.getTcNo()),
                    jobSeeker.getFirstname().toUpperCase(),
                    jobSeeker.getLastname().toUpperCase(),
                    jobSeeker.getBirthYear());
        } catch (Exception e) {
            System.out.println("MernisServiceAdapter: " + e.getMessage());
        }
        return false;
    }
}
