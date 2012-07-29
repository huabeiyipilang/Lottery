package cn.kli.lottery;

public class RuleFactory {
	public final static int RULE_RANDOM_NUMBER = 1;
	public static Rule creator(int ruleId){
		Rule rule = null;
		switch(ruleId){
		case RULE_RANDOM_NUMBER:
			rule = new RuleOfRandomNumber();
		}
		return rule;
	}
}
