package com.datagrandeur.neuropsych;
public class Singleton {


    private static Singleton instance;

    private int[] explosionPoints;

    private int trialSequence;
    private String userId;
    private String fullName;
    private String language = "en";
    private String location = "";
    private  double reward =0;

    private int currentPumpCount=0;

    private double currentTrialReward = 0;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getTrialSequence() {
        return trialSequence;
    }

    public void setTrialSequence(int trialSequence) {
        this.trialSequence = trialSequence;
    }

    public double getCurrentTrialReward() {
        return currentTrialReward;
    }

    public void setCurrentTrialReward(double currentTrialReward) {
        this.currentTrialReward = currentTrialReward;
    }

    public int getCurrentPumpCount() {
        return currentPumpCount;
    }

    public void setCurrentPumpCount(int currentPumpCount) {
        this.currentPumpCount = currentPumpCount;
    }

    public int[] getExplosionPoints() {
        return explosionPoints;
    }

    public void setExplosionPoints(int[] explosionPoints) {
        this.explosionPoints = explosionPoints;
    }
}