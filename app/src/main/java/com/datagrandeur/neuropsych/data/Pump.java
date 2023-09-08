package com.datagrandeur.neuropsych.data;

import java.time.LocalDateTime;

public class Pump {
    long trialId;//foreign key from trial id - autoincrement
    int trialSequence;//1-30
    int pumpSequence;
    String userId;
    int id;// pump id-primary key auto increment //we can search through this id as well
    LocalDateTime currentPumpTime;
    LocalDateTime lastPumpTime;
    String pumpBtwPumps;
    int balloonHeight;
    int balloonWidth;
    boolean exploded;

    public long getTrialId() {
        return trialId;
    }

    public void setTrialId(long trialId) {
        this.trialId = trialId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public LocalDateTime getCurrentPumpTime() {
        return currentPumpTime;
    }


    public void setCurrentPumpTime(LocalDateTime currentPumpTime) {
        this.currentPumpTime = currentPumpTime;
    }
    public LocalDateTime getLastPumpTime(){ return lastPumpTime;}

    public void setLastPumpTime(LocalDateTime lastPumpTime) {
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

    public Pump(int trialId, int pumpSequence, LocalDateTime currentPumpTime, String pumpBtwPumps, int balloonHeight, int balloonWidth, boolean exploded) {
        this.trialId = trialId;
        this.pumpSequence = pumpSequence;
        this.currentPumpTime = currentPumpTime;
        this.pumpBtwPumps = pumpBtwPumps;
        this.balloonHeight = balloonHeight;
        this.balloonWidth = balloonWidth;
        this.exploded = exploded;
    }
    public Pump(){

    }



}
