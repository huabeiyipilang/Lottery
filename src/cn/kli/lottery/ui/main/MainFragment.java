package cn.kli.lottery.ui.main;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import cn.kli.lottery.R;
import cn.kli.lottery.framework.base.BaseFragment;
import cn.kli.lottery.framework.utils.BlankActivity;
import cn.kli.lottery.ui.settings.SettingsFragment;

public class MainFragment extends BaseFragment {

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    public void initViews(View root) {
        setHasOptionsMenu(true);
    }

    @Override
    public void initDatas() {
        
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.action_settings:
            BlankActivity.startFragmentActivity(getActivity(), SettingsFragment.class, null);
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    
}
