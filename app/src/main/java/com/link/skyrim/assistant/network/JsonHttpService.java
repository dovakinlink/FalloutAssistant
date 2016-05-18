package com.link.skyrim.assistant.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by 焕超 on 2015/12/31.
 */
public class JsonHttpService extends JsonRequest<JsonElement> {

    private Response.Listener<JsonElement> mListener;

    public JsonHttpService(int method, String url, String requestBody, Response.Listener<JsonElement> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
        mListener = listener;
    }

    @Override
    protected Response<JsonElement> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JsonParser().parse(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JsonElement response) {
        mListener.onResponse(response);
    }
}
