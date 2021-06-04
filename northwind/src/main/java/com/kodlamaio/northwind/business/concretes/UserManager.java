package com.kodlamaio.northwind.business.concretes;

import com.kodlamaio.northwind.business.abstracts.UserService;
import com.kodlamaio.northwind.core.dataAccess.UserDao;
import com.kodlamaio.northwind.core.entities.User;
import com.kodlamaio.northwind.core.utilities.results.DataResult;
import com.kodlamaio.northwind.core.utilities.results.Result;
import com.kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import com.kodlamaio.northwind.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Added user");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email), "User found");
    }
}
