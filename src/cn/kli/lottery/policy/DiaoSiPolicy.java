package cn.kli.lottery.policy;

public class DiaoSiPolicy extends Policy {

    public DiaoSiPolicy() {
        super();
        addProbability(2, 50);
        addProbability(5, 20);
        addProbability(10, 10);
        addProbability(100, 1);
    }
    
}
