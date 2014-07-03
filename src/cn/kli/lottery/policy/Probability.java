package cn.kli.lottery.policy;

public class Probability {
    public final static int PROBABILITY_BASE = 1000;
    
    public int prize;
    public int probability;
    
    public int value_min;
    public int value_max;
    
    public Probability() {
    }

    public Probability(int prize, int probability) {
        this.prize = prize;
        this.probability = probability;
    }
    
}
