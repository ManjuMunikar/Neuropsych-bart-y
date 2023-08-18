package com.datagrandeur.neuropsych.data;


public class Trial {
     public int trialSequence;//1-30
     private String userId;
     private  int id;
     private double reward;
     private int pumpCount;

     private boolean popped;
    public int balloonStartHeight=80;
    public int balloonStartWidth=80;
    public int balloonEndHeight;
    public int balloonEndWidth;
    public String startTimeOfTrial;
    public String endTimeOfTrial;


    public Trial(int trialSequence, String timeBeforeFirstPump, String timeBtwLastPumpAndCollect) {
        this.trialSequence = trialSequence;
        this.startTimeOfTrial = timeBeforeFirstPump;
        this.endTimeOfTrial = timeBtwLastPumpAndCollect;
    }
    public Trial(){

    }

    public int getTrialSequence() {
        return trialSequence;
    }

    public void setTrialSequence(int trialSequence) {
        this.trialSequence = trialSequence;
    }
    public String getUserId(){ return userId;}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public int getBalloonEndHeight() {
        return balloonEndHeight;
    }

    public void setBalloonEndHeight(int balloonEndHeight) {
        this.balloonEndHeight = balloonEndHeight;
    }

    public int getBalloonEndWidth() {
        return balloonEndWidth;
    }

    public void setBalloonEndWidth(int balloonEndWidth) {
        this.balloonEndWidth = balloonEndWidth;
    }

    public String getStartTimeOfTrial() {
        return startTimeOfTrial;
    }

    public void setStartTimeOfTrial(String startTimeOfTrial) {
        this.startTimeOfTrial = startTimeOfTrial;
    }

    public String getEndTimeOfTrial() {
        return endTimeOfTrial;
    }

    public void setEndTimeOfTrial(String endTimeOfTrial) {
        this.endTimeOfTrial = endTimeOfTrial;
    }


    public int getPumpCount() {
        return pumpCount;
    }

    public void setPumpCount(int pumpCount) {
        this.pumpCount = pumpCount;
    }


    public boolean isPopped() {
        return popped;
    }

    public void setPopped(boolean popped) {
        this.popped = popped;
    }
}
