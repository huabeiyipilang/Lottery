package cn.kli.lottery;

import android.app.Application;

public class App extends Application {
    
    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        initDb();
        initManagers();
    }

    private void initDb(){
        
    }
    
    private void initManagers(){
    }
    
    public static App getContext(){
        return sApp;
    }
}
