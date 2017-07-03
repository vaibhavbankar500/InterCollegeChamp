package com.example.vaibhav.intercollegechamp;

import android.app.Application;

import com.baasbox.android.BaasBox;

/**
 * Created by vaibhav on 31/3/17.
 */

public class BaasConnection extends Application {
    public static BaasBox bbox;

    public void onCreate(){
        super.onCreate();
        BaasBox.Config config = new BaasBox.Config();
        config.apiDomain = "192.168.43.110";//TODO change to real IP for device or remote server test
        config.authenticationType = BaasBox.Config.AuthType.SESSION_TOKEN;

        bbox = BaasBox.initDefault(this, config);

    }
}
