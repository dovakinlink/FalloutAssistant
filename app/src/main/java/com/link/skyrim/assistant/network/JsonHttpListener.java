package com.link.skyrim.assistant.network;

import com.android.volley.VolleyError;
import com.google.gson.JsonElement;

/**
 * Created by 焕超 on 2015/12/31.
 */
public interface JsonHttpListener {

    public void onSuccess(Object tag, JsonElement response);

    public void onFailed(VolleyError error);
}
