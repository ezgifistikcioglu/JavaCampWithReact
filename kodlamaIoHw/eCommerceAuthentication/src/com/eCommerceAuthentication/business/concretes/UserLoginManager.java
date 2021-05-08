package com.eCommerceAuthentication.business.concretes;

import com.eCommerceAuthentication.business.abstracts.UserLoginService;
import com.eCommerceAuthentication.core.NotifyService;
import com.eCommerceAuthentication.dataAccess.abstracts.UserDao;
import com.eCommerceAuthentication.entities.concretes.User;

public class UserLoginManager implements UserLoginService {
   private NotifyService notifyService ;
   private UserDao userDao;

    public UserLoginManager(NotifyService notifyService, UserDao userDao) {
        this.notifyService = notifyService;
        this.userDao = userDao;
    }

    @Override
    public void register(User user) {
        if (!userDao.addVerificationEmail(user)){
            System.out.println("Registration could not be performed. This mail is available in the system. " + user.getEmail() + " Please enter your valid password.");
        }else if(!notifyService.addNewAccount(user)){
            System.out.println("Registration could not be performed.");
        }else {
            notifyService.isClickedToValidationLink(user);
            userDao.addAccount(user);
        }
    }

    @Override
    public void login(String email, String password) {
        if (userDao.signUp(email,password)){
            System.out.println(email + "Login succeeded. Welcome");
        }else {
            System.out.println("Login is not verified, please try again.");
        }

    }
}
