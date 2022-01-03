package com.robinsgl.dungeonsanddragonsspelllist.app;

import android.app.Application;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SpellListApp extends Application {
    private static SpellListApp instance;
    private RequestQueue requestQueue;

    public static synchronized SpellListApp getInstance() {
        if (instance == null) {
            instance = new SpellListApp();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
