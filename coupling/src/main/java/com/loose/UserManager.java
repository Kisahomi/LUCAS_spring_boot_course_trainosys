package com.loose;

public class UserManager {
    private final UserDataProvider userDataProvider;

   
    public UserManager(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public String getUserInfo() {
        return userDataProvider.getUserDetails();
    }
}