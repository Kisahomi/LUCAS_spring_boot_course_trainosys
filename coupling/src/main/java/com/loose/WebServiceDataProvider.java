package com.loose;

public class WebServiceDataProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "User Details From Web Service";
    }
}