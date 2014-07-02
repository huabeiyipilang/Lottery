package cn.kli.lottery;

import cn.kli.lottery.framework.base.BaseActivity;
import cn.kli.lottery.ui.main.MainFragment;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentFragment(MainFragment.class, null);
    }

}
