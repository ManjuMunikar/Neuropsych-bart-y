package com.datagrandeur.neuropsych.data;


public class Trial {
     public int trialSequence;//1-30
     private int userId;
     private  int id;
     private double reward;//0.5$
    public int balloonStartHeight=233;
    public int balloonStartWidth=199;
    public String timeBeforeFirstPump;
    public String timeBtwLastPumpAndCollect;


    public Trial(int trialSequence, String timeBeforeFirstPump, String timeBtwLastPumpAndCollect) {
        this.trialSequence = trialSequence;
        this.timeBeforeFirstPump = timeBeforeFirstPump;
        this.timeBtwLastPumpAndCollect = timeBtwLastPumpAndCollect;
    }

    public int getTrialSequence() {
        return trialSequence;
    }

    public void setTrialSequence(int trialSequence) {
        this.trialSequence = trialSequence;
    }

    public int getBalloonStartHeight() {
        return balloonStartHeight;
    }

    public void setBalloonStartHeight(int balloonStartHeight) {
        this.balloonStartHeight = balloonStartHeight;
    }

    public int getBalloonStartWidth() {
        return balloonStartWidth;
    }

    public void setBalloonStartWidth(int balloonStartWidth) {
        this.balloonStartWidth = balloonStartWidth;
    }

    public String getTimeBeforeFirstPump() {
        return timeBeforeFirstPump;
    }

    public void setTimeBeforeFirstPump(String timeBeforeFirstPump) {
        this.timeBeforeFirstPump = timeBeforeFirstPump;
    }

    public String getTimeBtwLastPumpAndCollect() {
        return timeBtwLastPumpAndCollect;
    }

    public void setTimeBtwLastPumpAndCollect(String timeBtwLastPumpAndCollect) {
        this.timeBtwLastPumpAndCollect = timeBtwLastPumpAndCollect;
    }









}
