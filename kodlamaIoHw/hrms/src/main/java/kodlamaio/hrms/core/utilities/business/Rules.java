package kodlamaio.hrms.core.utilities.business;

import kodlamaio.hrms.core.utilities.results.Result;
import lombok.var;

public class Rules {
    public static Result run(Result... results) {
        for (var logic : results) {
            if (!logic.isSuccess()) {
                return logic;
            }
        }
        return null;
    }
}
