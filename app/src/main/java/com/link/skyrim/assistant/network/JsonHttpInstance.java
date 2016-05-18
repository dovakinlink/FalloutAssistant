package com.link.skyrim.assistant.network;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;

/**
 * Created by 焕超 on 2015/12/31.
 */
public class JsonHttpInstance {

    private String request_url = "";
    private RequestQueue mRequestQueue;
    private Context mContext;
    private JsonHttpListener mListener;

    private  int httpTimeout = 10000;

    private JsonHttpInstance(){}

    private JsonHttpInstance(Context context, JsonHttpListener listener){
        this.mRequestQueue = Volley.newRequestQueue(context);
        this.mContext = context;
        this.mListener = listener;
    }

    public static JsonHttpInstance Build(Context context, JsonHttpListener listener){
        return new JsonHttpInstance(context,listener);
    }

    public JsonHttpInstance setUrl(String url){
        this.request_url = url;
        return this;
    }

    public JsonHttpInstance setTimeOut(int timeOut){
        this.httpTimeout = timeOut;
        return this;
    }

    public JsonHttpInstance addRequest(int method, final Object tag , JsonElement params){
        String requestParams = "";
        if(params != null) requestParams = params.toString();

        final JsonHttpService mService = new JsonHttpService(method, this.request_url, requestParams,
                new Response.Listener<JsonElement>() {
            @Override
            public void onResponse(JsonElement response) {
                mListener.onSuccess(tag,response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mListener.onFailed(error);
            }
        });

        mService.setTag(tag);
        mService.setRetryPolicy(new DefaultRetryPolicy(
                httpTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mService);
        return this;
    }

    public void request(){
        mRequestQueue.start();
    }

    public void cancel(){
        if (mRequestQueue != null)  mRequestQueue.cancelAll(mContext);
    }


}
