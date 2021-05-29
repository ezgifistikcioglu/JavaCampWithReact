package kodlamaio.hrms.core.adapters.concretes;

import kodlamaio.hrms.core.adapters.abstracts.MernisCheckService;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import services.mernisService.GSPKPSPublicSoap;

public class MernisServiceAdapter implements MernisCheckService {

    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {
        GSPKPSPublicSoap publicSoap = new GSPKPSPublicSoap();
        try {
            return publicSoap.TCKimlikNoDogrula(
                    Long.parseLong(jobSeeker.getTcNo()),
                    jobSeeker.getFirstname().toUpperCase(),
                    jobSeeker.getLastname().toUpperCase(),
                    jobSeeker.getBirthYear().getYear());
        } catch (Exception e) {
            System.out.println("MernisServiceAdapter: " + e.getMessage());
        }
        return false;
    }
}
