package cn.kli.lottery.policy;

import java.util.ArrayList;
import java.util.List;

public abstract class Policy {

    private List<Probability> mProbabilities = new ArrayList<Probability>();

    public void addProbability(int prize, int probability) {
        removeProbability(prize);
        mProbabilities.add(new Probability(prize, probability));
    }

    public void removeProbability(int prize) {
        Probability tmp = null;
        for (Probability probability : mProbabilities) {
            if(prize == probability.prize){
                tmp = probability;
                break;
            }
        }
        if(tmp != null){
            mProbabilities.remove(tmp);
        }
    }

    public float getPrizePerTime() {
        float res = 0f;
        for (Probability probability : mProbabilities) {
            res += Float.valueOf(probability.prize)
                    + Float.valueOf(probability.probability) / Float.valueOf(Probability.PROBABILITY_BASE);
        }
        return res;
    }
}
