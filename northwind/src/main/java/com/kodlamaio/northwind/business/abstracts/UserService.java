package com.kodlamaio.northwind.business.abstracts;

import com.kodlamaio.northwind.core.entities.User;
import com.kodlamaio.northwind.core.utilities.results.DataResult;
import com.kodlamaio.northwind.core.utilities.results.Result;

public interface UserService {
    Result add(User user);
    DataResult<User> findByEmail(String email);
}
