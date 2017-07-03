package com.example.vaibhav.intercollegechamp;

/**
 * Created by vaibhav on 17/4/17.
 */

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baasbox.android.BaasBox;

import com.android.volley.toolbox.ImageLoader;
import com.example.vaibhav.intercollegechamp.util.LruBitmapCache;


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public static BaasBox bbox;
    private RequestQueue mRequestQueue;

    private static AppController mInstance;


    private ImageLoader mImageLoader;


    @Override
    public void onCreate() {




        super.onCreate();
        BaasBox.Config config = new BaasBox.Config();
        config.apiDomain = "http://192.168.43.18";//TODO change to real IP for device or remote server test
        config.authenticationType = BaasBox.Config.AuthType.SESSION_TOKEN;

        bbox = BaasBox.initDefault(this, config);

        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }



    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }


}