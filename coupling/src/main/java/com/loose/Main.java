package com.loose;

public class Main {
    public static void main(String[] args) {
  
        UserDataProvider databaseProvider = new UserDatabaseProvider();
        UserManager userManagerWithDb = new UserManager(databaseProvider);
        System.out.println(userManagerWithDb.getUserInfo());

   
        UserDataProvider webServiceProvider = new WebServiceDataProvider();
        UserManager userManagerWithApi = new UserManager(webServiceProvider);
        System.out.println(userManagerWithApi.getUserInfo());

        UserDataProvider newDbProvider = new NewDatabaseProvider();
        UserManager userManagerWithNewDb = new UserManager(newDbProvider);
        System.out.println(userManagerWithNewDb.getUserInfo());
    
    }
}