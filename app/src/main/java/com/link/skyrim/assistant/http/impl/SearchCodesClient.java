package com.link.skyrim.assistant.http.impl;

import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.link.skyrim.assistant.bean.SearchCodeRequest;
import com.link.skyrim.assistant.http.WorkClient;
import com.link.skyrim.assistant.network.JsonHttpInstance;
import com.link.skyrim.assistant.network.JsonHttpListener;

/**
 * Created by inven on 2016/3/9.
 */
public class SearchCodesClient extends WorkClient {

    private final String method = "/service/searchcode.do";

    @Override
    public WorkClient init(Context context, JsonHttpListener listener, String TAG) {
        instance = JsonHttpInstance.Build(context, listener)
                .setUrl(getUrl(method));
        mTAG = TAG;
        return this;
    }

    @Override
    public WorkClient constructParams(Object... obj) {
        instance.addRequest(Request.Method.POST, mTAG,getParams(obj[0].toString()));
        return this;
    }

    @Override
    public void invoke() {
        instance.request();
    }

    private JsonElement getParams(String content){
        SearchCodeRequest scr = new SearchCodeRequest();
        scr.setContent(content);
        return new Gson().toJsonTree(scr, SearchCodeRequest.class);
    }
}
