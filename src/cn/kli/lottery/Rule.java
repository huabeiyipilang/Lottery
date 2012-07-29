package cn.kli.lottery;

public abstract class Rule {
	
	class Result{
		public int prize;
		public int roll;
	}
	
	abstract Result execute();
	
}
