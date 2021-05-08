package com.eCommerceAuthentication.business.abstracts;

import com.eCommerceAuthentication.entities.concretes.User;

public interface UserLoginService {
    void register(User user);
    void login(String email, String password);
}
