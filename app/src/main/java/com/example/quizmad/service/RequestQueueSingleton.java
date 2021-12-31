package com.example.quizmad.service;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.quizmad.R;

public class RequestQueueSingleton {

    private static RequestQueueSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private RequestQueueSingleton(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueSingleton getInstances(Context context){
        if(instance == null){
            instance = new RequestQueueSingleton(context);
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(this.context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
