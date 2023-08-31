package com.datagrandeur.neuropsych;

public class SingletonPractice {

    private static SingletonPractice instance;
    private int instructionIndex;

    private float currentTrialReward = 0;
    private boolean pop = false;

    private float points;

    public static SingletonPractice getInstance() {
        if (instance == null)
            instance = new SingletonPractice();
        return instance;
    }

    public int getInstructionIndex() {
        return instructionIndex;
    }

    public void setInstructionIndex(int instructionIndex) {
        this.instructionIndex = instructionIndex;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public float getCurrentTrialReward() {
        return currentTrialReward;
    }

    public void setCurrentTrialReward(float currentTrialReward) {
        this.currentTrialReward = currentTrialReward;
    }

    public boolean isPop() {
        return pop;
    }

    public void setPop(boolean pop) {
        this.pop = pop;
    }
}
