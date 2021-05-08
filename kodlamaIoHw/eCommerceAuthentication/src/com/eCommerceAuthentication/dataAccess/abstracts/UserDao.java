package com.eCommerceAuthentication.dataAccess.abstracts;


import com.eCommerceAuthentication.entities.concretes.User;

import java.util.List;

public interface UserDao {
    void addAccount(User user);
    boolean addVerificationEmail(User user);
    boolean signUp(String email, String password);

    User getUser(int id);
    List<User> getAllUser();
}
