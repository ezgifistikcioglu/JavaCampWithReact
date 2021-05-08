package com.eCommerceAuthentication.core;

import com.eCommerceAuthentication.entities.concretes.User;
import com.eCommerceAuthentication.googleAuthentication.GoogleAuthManager;

public class GoogleAuthManagerAdapter implements NotifyService {

    @Override
    public boolean addNewAccount(User user) {
        GoogleAuthManager authManager = new GoogleAuthManager();

        if (authManager.loginWithGoogle(user.getEmail())){
            System.out.println("Authentication credentials succeeded.");
            return true;
        }
        System.out.println("Authentication credentials fail.");
        return false;
    }

    @Override
    public void sendValidationEmail(User user, String content) {

    }

    @Override
    public void sendValidationLink(User user) {

    }

    @Override
    public boolean isClickedToValidationLink(User user) {
        return true;
    }
}
