package com.link.skyrim.assistant;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.link.skyrim.assistant.bean.CodeInfo;
import com.link.skyrim.assistant.http.impl.SearchCodesClient;
import com.link.skyrim.assistant.network.JsonHttpListener;
import com.link.skyrim.assistant.system.SysConst;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements JsonHttpListener{

    private EditText edt_search;
    private ImageButton btn_search;
    private ListView list_search;
    private SearchListAdapter adapter;
    List<CodeInfo> codeInfoList = new ArrayList<CodeInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupUI();
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = edt_search.getEditableText().toString();
                if(searchStr.isEmpty()){
                    Snackbar.make(v, "empty!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }

                new SearchCodesClient().init(SearchActivity.this,SearchActivity.this, SysConst.Method.TAG_SEARCHCODE)
                        .constructParams(searchStr).invoke();
            }
        });
    }

    private void setupUI(){
        edt_search = (EditText) findViewById(R.id.edt_search);
        btn_search = (ImageButton) findViewById(R.id.btn_search);
        list_search = (ListView) findViewById(R.id.list_search);

        adapter = new SearchListAdapter(SearchActivity.this,codeInfoList);
        list_search.setAdapter(adapter);
    }

    @Override
    public void onSuccess(Object tag, JsonElement response) {

        String str_tag = tag.toString();
        if(str_tag.equals(SysConst.Method.TAG_SEARCHCODE)){
            codeInfoList.clear();
            if(response != null){
                JsonElement tempJson = response;
                JsonArray ja = tempJson.getAsJsonArray();
                for(int i = 0; i < ja.size(); i++){
                    JsonObject jo = ja.get(i).getAsJsonObject();
                    CodeInfo ci = new CodeInfo();
                    ci.setC_code_name(jo.get("c_code_name").getAsString());
                    ci.setC_code_value(jo.get("c_code_value").getAsString());
                    Object o = jo.get("c_code_des");
                    if(o != null)
                        ci.setC_code_des(jo.get("c_code_des").getAsString());
                    else
                        ci.setC_code_des("");

                    codeInfoList.add(ci);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFailed(VolleyError error) {
        codeInfoList.clear();
    }
}
