package com.eCommerceAuthentication.core;

import com.eCommerceAuthentication.entities.concretes.User;

public interface NotifyService {
    boolean addNewAccount(User user);
    void sendValidationEmail(User user, String content);
    void sendValidationLink(User user);
    boolean isClickedToValidationLink(User user);
}
