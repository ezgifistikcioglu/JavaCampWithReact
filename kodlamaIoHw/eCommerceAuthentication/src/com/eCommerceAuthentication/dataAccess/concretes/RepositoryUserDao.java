package com.eCommerceAuthentication.dataAccess.concretes;

import com.eCommerceAuthentication.dataAccess.abstracts.UserDao;
import com.eCommerceAuthentication.entities.concretes.User;

import java.util.ArrayList;
import java.util.List;

public class RepositoryUserDao implements UserDao {
    List<User> users = new ArrayList<>();

    @Override
    public void addAccount(User user) {
        users.add(user);
        System.out.println("User added by => user name : " + user.getName() + " user surname : " + user.getSurname());
    }

    @Override
    public boolean addVerificationEmail(User user) {
        for (User usr : users)
            if (usr.getEmail() == user.getEmail()) {
                return false;
            }
        return true;
    }

    @Override
    public boolean signUp(String email, String password) {
        for (User usr : users) {
            if (usr.getEmail().equals(email) && usr.getPassword().equals(password))
                return true;
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}
