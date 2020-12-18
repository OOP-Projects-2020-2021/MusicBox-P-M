package com.company;

public class User {
    private String username;
    private String password;
    private String name;
    private String imagePath;
    public User(String username,String password, String name, String imagePath)
    {
        this.username=username;
        this.password=password;
        this.name=name;
        this.imagePath=imagePath;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
