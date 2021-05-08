package com.eCommerceAuthentication;

import com.eCommerceAuthentication.business.abstracts.UserLoginService;
import com.eCommerceAuthentication.business.concretes.UserLoginManager;
import com.eCommerceAuthentication.core.GoogleAuthManagerAdapter;
import com.eCommerceAuthentication.core.NotifyManager;
import com.eCommerceAuthentication.dataAccess.concretes.RepositoryUserDao;
import com.eCommerceAuthentication.entities.concretes.User;

public class Main {

    public static void main(String[] args) {
        User user1 = new User(1,"ezgi","fstk","985720","bid@gmail.com");
        User user2 = new User(1,"e","f","9720","bid@gmail.com");

        UserLoginService userLoginService = new UserLoginManager(new NotifyManager(true),new RepositoryUserDao());
        userLoginService.register(user1);
        System.out.println("--------------------------------------------");
        userLoginService.register(user2);

        userLoginService.login("bid@gmail.com","985720");

        UserLoginService userLoginService2 = new UserLoginManager(new GoogleAuthManagerAdapter(),new RepositoryUserDao());
        userLoginService2.register(user1);
        System.out.println("--------------------------------------------");
        userLoginService2.register(user2);

        userLoginService2.login("bid@gmail.com","985720");

    }
}
