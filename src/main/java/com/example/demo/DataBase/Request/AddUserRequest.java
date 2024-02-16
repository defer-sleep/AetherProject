package com.example.demo.DataBase.Request;

public class AddUserRequest {
    private String userName;
    private String email, password;
    private String key;

    public AddUserRequest(String userName, String email, String password, String key) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.key = key;
        System.out.println(userName + " " + email + " " + password + " " + key);
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }
}
