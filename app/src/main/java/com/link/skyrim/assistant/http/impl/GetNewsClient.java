package com.link.skyrim.assistant.http.impl;

import android.content.Context;

import com.android.volley.Request;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.link.skyrim.assistant.bean.AddCourseRequest;
import com.link.skyrim.assistant.bean.AddCourseResBean;
import com.link.skyrim.assistant.bean.AddPaitentRequest;
import com.link.skyrim.assistant.bean.GetNoticeRequest;
import com.link.skyrim.assistant.bean.GetPaitentRequest;
import com.link.skyrim.assistant.bean.LoginRequest;
import com.link.skyrim.assistant.bean.ReqBean;
import com.link.skyrim.assistant.bean.SearchCodeRequest;
import com.link.skyrim.assistant.bean.SearchPaitentRequest;
import com.link.skyrim.assistant.http.WorkClient;
import com.link.skyrim.assistant.network.JsonHttpInstance;
import com.link.skyrim.assistant.network.JsonHttpListener;
import com.link.skyrim.assistant.system.SysConst;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by inven on 2016/3/2.
 */
public class GetNewsClient extends WorkClient {

    /*private final String method = "/service/getnews.do";*/
    private final String method = "/mobile/http/api.do";

    @Override
    public WorkClient init(Context context,JsonHttpListener listener, String TAG) {
        instance = JsonHttpInstance.Build(context,listener)
                .setUrl(getUrl(method));
        mTAG = TAG;
        return this;
    }

    @Override
    public WorkClient constructParams(Object... obj) {

        instance.addRequest(Request.Method.POST, mTAG,getParams(null));
        return this;
    }

    @Override
    public void invoke() {
        instance.request();
    }

    private JsonElement getParams(String content){
        ReqBean<AddCourseRequest> rb = new ReqBean<AddCourseRequest>();
        rb.setMethod("AddCourse");
        rb.setRequestContent("nothing");
        rb.setSessionID("7B6D6B5F62D25241FA8599D040C7A5DA");
       /* LoginRequest request = new LoginRequest();
        request.setUsername("13462380743");
        request.setPassword("123456");*/

/*        GetNoticeRequest request = new GetNoticeRequest();
        request.setOrg_id("6295");*/

       /* GetPaitentRequest request = new GetPaitentRequest();
        request.getPageInfo().setCurrPage(1);
        request.getPageInfo().setPageSize(20);*/

/*        SearchPaitentRequest request = new SearchPaitentRequest();
        request.setContent("凯声");
        rb.setParams(request);*/

/*        AddPaitentRequest request = new AddPaitentRequest();
        request.setName("尼古拉斯");
        request.setSex("1");
        request.setPhone("18888888888");
        request.setBirthday(new Date(System.currentTimeMillis()));
        request.setMarriage(1);
        request.setAddress("地球");*/

        AddCourseRequest request = new AddCourseRequest();
        request.setContent("诊断报告显示没卵事");
        request.setDate(new Date(System.currentTimeMillis()));
        request.setName("小明");
        request.setRecord_id(1001);

        for(int i = 0; i < 3; i++){
            AddCourseResBean bean = new AddCourseResBean();
            bean.setPicture("http://url1");

            request.getPicture().add(bean);
        }

        rb.setParams(request);

        return new Gson().toJsonTree(rb, ReqBean.class);
    }
}
