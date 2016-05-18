package com.link.skyrim.assistant.bean;

/**
 * Created by inven on 2016/3/23.
 */
public class ReqBean<T> {
    /**
     * 一个字符串，指定JSON-RPC协议版本，目前使用”2.0”
     */
    private String jsonrpc = "2.0";

    /**
     * 调用方法
     */
    private String method;

    /**
     * 客户端信息
     */
    private String clientInfo;

    /**
     * sessionID
     */
    private String sessionID;

    /**
     * 请求内容
     */
    private String requestContent;
    /**
     * 请求参数
     */
    private T params;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
