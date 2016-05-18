package com.link.skyrim.assistant.http.impl;

import android.content.Context;

import com.android.volley.Request;
import com.link.skyrim.assistant.http.WorkClient;
import com.link.skyrim.assistant.network.JsonHttpInstance;
import com.link.skyrim.assistant.network.JsonHttpListener;

/**
 * Created by inven on 2016/3/8.
 */
public class InitDataClient extends WorkClient {

    private final String method = "/service/initdata.do";

    @Override
    public WorkClient init(Context context, JsonHttpListener listener, String TAG) {
        instance = JsonHttpInstance.Build(context, listener)
                .setUrl(getUrl(method));
        mTAG = TAG;
        return this;
    }

    @Override
    public WorkClient constructParams(Object... obj) {
        instance.addRequest(Request.Method.POST, mTAG,null);
        return this;
    }

    @Override
    public void invoke() {
        instance.request();
    }
}
