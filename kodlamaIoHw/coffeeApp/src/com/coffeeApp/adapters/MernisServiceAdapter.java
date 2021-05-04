package com.coffeeApp.adapters;

import com.coffeeApp.entities.Customer;
import com.coffeeApp.interfaces.CustomerCheckService;
import com.coffeeApp.mernisService.IRIKPSPublicSoap;

public class MernisServiceAdapter implements CustomerCheckService {
    @Override
    public boolean checkIfRealPerson(Customer customer) {
        IRIKPSPublicSoap soap = new IRIKPSPublicSoap();

        try {
            return soap.TCKimlikNoDogrula(
                    Long.parseLong(customer.nationalityId),
                    customer.firstName.toUpperCase(),
                    customer.lastName.toUpperCase(),
                    customer.dateOfBirth.getYear());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
