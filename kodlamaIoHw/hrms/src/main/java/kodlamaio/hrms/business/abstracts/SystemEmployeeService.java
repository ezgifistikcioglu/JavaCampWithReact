package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface SystemEmployeeService {
    boolean giveConfirmation();
    Result add(int id);
}
