package com.virtualstudios.medistore.data.volley;


import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {

    private static VolleyHelper mInstance = null;
    private RequestQueue mRequestQueue;
    private  Context mContext;

    private VolleyHelper() {

    }

    public static synchronized VolleyHelper getInstance() {
        if (mInstance == null) {
            mInstance = new VolleyHelper();
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue(Context context) {
        mContext = context;
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context);
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue(mContext).add(req);
    }

}