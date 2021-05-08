package com.eCommerceAuthentication.googleAuthentication;

public class GoogleAuthManager {
    public boolean loginWithGoogle(String email){
        System.out.println("Signed in with Google Authentication. " + email);
        return true;
    }
}
