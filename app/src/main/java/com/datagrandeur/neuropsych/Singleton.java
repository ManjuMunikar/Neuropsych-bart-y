package com.datagrandeur.neuropsych;
public class Singleton {


    private static Singleton instance;



    public int getTrialSequence() {
        return trialSequence;
    }

    public void setTrialSequence(int trialSequence) {
        this.trialSequence = trialSequence;
    }

    private int trialSequence;

    private String userId;
    private String fullname;
    private String language = "en";
    private String location = "";
    private  int reward =0;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }


    private Singleton() {
    }
    public int getReward(){  return reward;}
    public void setReward(int reward){
        this.reward=reward;
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