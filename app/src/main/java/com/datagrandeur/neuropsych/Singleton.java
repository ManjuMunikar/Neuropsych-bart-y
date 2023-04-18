package com.datagrandeur.neuropsych;
public class Singleton {


    private static Singleton instance;

    private String userId;
    private String fullname;
    private String language = "en";
    private String location = "";

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }


    private Singleton() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}