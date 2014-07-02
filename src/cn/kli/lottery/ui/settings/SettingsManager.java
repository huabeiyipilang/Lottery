package cn.kli.lottery.ui.settings;

import cn.kli.lottery.framework.utils.PrefUtils;

public class SettingsManager {
    final static int LOTTERY_COUNT_MIN = 1;
    final static int LOTTERY_COUNT_MAX = 5;
    
    final static int LOTTERY_PRIZE_MIN = 1;
    final static int LOTTERY_PRIZE_MAX = 20;
    
    /**
     * 设置每日抽奖次数
     * @Title: setLotteryCount
     * @param count
     * @return void
     * @date 2014-7-3 上午12:46:27
     */
    public static void setLotteryCount(int count){
        PrefUtils.putInt("lottery_count", count);
    }
    
    /**
     * 获取每日抽奖次数
     * @Title: getLotteryCount
     * @return
     * @return int
     * @date 2014-7-3 上午12:46:49
     */
    public static int getLotteryCount(){
        return PrefUtils.getInt("lottery_count", 1);
    }
    
    /**
     * 设置每次中奖平均值
     * @Title: setLotteryPrize
     * @param prize
     * @return void
     * @date 2014-7-3 上午12:47:02
     */
    public static void setLotteryPrize(int prize){
        PrefUtils.putInt("lottery_prize", prize);
    }
    
    /**
     * 获取每次中奖平均值
     * @Title: getLotteryPrize
     * @return
     * @return int
     * @date 2014-7-3 上午12:47:19
     */
    public static int getLotteryPrize(){
        return PrefUtils.getInt("lottery_prize", 1);
    }
}
