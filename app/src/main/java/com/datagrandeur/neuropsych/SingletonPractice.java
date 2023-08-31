package com.datagrandeur.neuropsych;

public class SingletonPractice {

    private static SingletonPractice instance;
    private int instructionIndex;

    private int currentTrialReward = 0;
    private boolean pop = false;

    private int points;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCurrentTrialReward() {
        return currentTrialReward;
    }

    public void setCurrentTrialReward(int currentTrialReward) {
        this.currentTrialReward = currentTrialReward;
    }

    public boolean isPop() {
        return pop;
    }

    public void setPop(boolean pop) {
        this.pop = pop;
    }
}
