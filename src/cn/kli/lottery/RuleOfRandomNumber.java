package cn.kli.lottery;

import java.util.Random;

public class RuleOfRandomNumber extends Rule {

	@Override
	public Result execute() {
		Result result = new Result();
		result.roll = getRandomNumber(100);
		result.prize = checkPrize(result.roll);
		return result;
	}

	private int getRandomNumber(int max){
		Random random = new Random();
    	return random.nextInt(100);
	}
	
	private int checkPrize(int number){
    	if(number >= 1 && number <= 20){
    		return 2;
    	}else if(number >=21 && number <= 30){
    		return 4;
    	}else if(number >= 31 && number <= 32){
    		return 10;
    	}else{
    		return 0;
    	}
	}
}
