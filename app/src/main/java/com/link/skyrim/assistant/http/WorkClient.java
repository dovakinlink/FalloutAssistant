package com.link.skyrim.assistant.http;

import android.content.Context;

import com.link.skyrim.assistant.network.JsonHttpInstance;
import com.link.skyrim.assistant.network.JsonHttpListener;
import com.link.skyrim.assistant.system.SysConst;

/**
 * Created by inven on 2016/3/2.
 */
public abstract class WorkClient {

    protected JsonHttpInstance instance = null;
    protected String mTAG = "";

    public String getUrl(String method){
        StringBuilder url = new StringBuilder("http://" + SysConst.URL.ip + ":" + SysConst.URL.port);
        url.append("/platform");
        url.append(method);
        return url.toString();
    }

    public abstract WorkClient init(Context context,JsonHttpListener listener, String TAG);

    public abstract WorkClient constructParams(Object... obj);

    public abstract void invoke();
}
