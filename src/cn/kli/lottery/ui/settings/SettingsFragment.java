package cn.kli.lottery.ui.settings;

import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import cn.kli.lottery.R;
import cn.kli.lottery.framework.base.BaseFragment;

public class SettingsFragment extends BaseFragment implements OnSeekBarChangeListener {
    
    private SeekBar mLotteryCountView;
    private TextView mCountDisplayView;
    private SeekBar mLotteryPrizeView;
    private TextView mPrizeDisplayView;

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_settings;
    }

    @Override
    public void initViews(View root) {
        mLotteryCountView = (SeekBar)root.findViewById(R.id.sb_lottery_count);
        mLotteryPrizeView = (SeekBar)root.findViewById(R.id.sb_prize);
        mCountDisplayView = (TextView)root.findViewById(R.id.tv_lottery_count);
        mPrizeDisplayView = (TextView)root.findViewById(R.id.tv_prize);

        mCountDisplayView.setText(SettingsManager.getLotteryCount()+"次");
        mPrizeDisplayView.setText(SettingsManager.getLotteryPrize()+"元");
        
        mLotteryCountView.setMax(value2Progress(SettingsManager.LOTTERY_COUNT_MAX));
        mLotteryPrizeView.setMax(value2Progress(SettingsManager.LOTTERY_PRIZE_MAX));

        mLotteryCountView.setProgress(value2Progress(SettingsManager.getLotteryCount()));
        mLotteryPrizeView.setProgress(value2Progress(SettingsManager.getLotteryPrize()));

        mLotteryCountView.setOnSeekBarChangeListener(this);
        mLotteryPrizeView.setOnSeekBarChangeListener(this);
    }

    @Override
    public void initDatas() {
        
    }
    
    private int value2Progress(int value){
        return value - 1;
    }
    
    private int progress2Value(int progress){
        return progress + 1;
    }

    @Override
    public void onProgressChanged(SeekBar view, int progress, boolean arg2) {
        switch(view.getId()){
        case R.id.sb_lottery_count:
            mCountDisplayView.setText(progress2Value(progress)+"次");
            break;
        case R.id.sb_prize:
            mPrizeDisplayView.setText(progress2Value(progress)+"元");
            break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar arg0) {
        
    }

    @Override
    public void onStopTrackingTouch(SeekBar view) {
        switch(view.getId()){
        case R.id.sb_lottery_count:
            SettingsManager.setLotteryCount(progress2Value(view.getProgress()));
            break;
        case R.id.sb_prize:
            SettingsManager.setLotteryPrize(progress2Value(view.getProgress()));
            break;
        }
    }

}
