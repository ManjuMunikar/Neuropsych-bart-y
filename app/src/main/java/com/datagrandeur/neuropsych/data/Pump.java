package com.datagrandeur.neuropsych.data;

public class Pump {
    int trialId;//foreign key from trial id - autoincrement
    int trialSequence;//1-30
    int pumpSequence;
    int id;// pump id-primary key auto increment //we can search through this id as well
    String currentPumpTime;
    String lastPumpTime;
    String pumpBtwPumps;
    int balloonHeight;
    int balloonWidth;
    boolean exploded;

    public int getTrialId() {
        return trialId;
    }

    public void setTrialId(int trialId) {
        this.trialId = trialId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrialSequence() {
        return trialSequence;
    }

    public void setTrialSequence(int trialSequence) {
        this.trialSequence = trialSequence;
    }

    public int getPumpSequence() {
        return pumpSequence;
    }

    public void setPumpSequence(int pumpSequence) {
        this.pumpSequence = pumpSequence;
    }

    public String getCurrentPumpTime() {
        return currentPumpTime;
    }


    public void setCurrentPumpTime(String currentPumpTime) {
        this.currentPumpTime = currentPumpTime;
    }
    public String getLastPumpTime(){ return lastPumpTime;}

    public void setLastPumpTime(String lastPumpTime) {
        this.lastPumpTime = lastPumpTime;
    }

    public String getPumpBtwPumps() {
        return pumpBtwPumps;
    }

    public void setPumpBtwPumps(String pumpBtwPumps) {
        this.pumpBtwPumps = pumpBtwPumps;
    }

    public int getBalloonHeight() {
        return balloonHeight;
    }

    public void setBalloonHeight(int balloonHeight) {
        this.balloonHeight = balloonHeight;
    }

    public int getBalloonWidth() {
        return balloonWidth;
    }

    public void setBalloonWidth(int balloonWidth) {
        this.balloonWidth = balloonWidth;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Pump(int trialId, int pumpSequence, String currentPumpTime, String pumpBtwPumps, int balloonHeight, int balloonWidth, boolean exploded) {
        this.trialId = trialId;
        this.pumpSequence = pumpSequence;
        this.currentPumpTime = currentPumpTime;
        this.pumpBtwPumps = pumpBtwPumps;
        this.balloonHeight = balloonHeight;
        this.balloonWidth = balloonWidth;
        this.exploded = exploded;
    }



}
