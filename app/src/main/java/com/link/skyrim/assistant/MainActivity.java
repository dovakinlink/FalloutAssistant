package com.link.skyrim.assistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.link.skyrim.assistant.bean.CodeInfo;
import com.link.skyrim.assistant.bean.NewsBean;
import com.link.skyrim.assistant.http.impl.GetNewsClient;
import com.link.skyrim.assistant.http.impl.InitDataClient;
import com.link.skyrim.assistant.http.impl.SearchCodesClient;
import com.link.skyrim.assistant.network.JsonHttpListener;
import com.link.skyrim.assistant.system.SysConst;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,JsonHttpListener ,OnRefreshListener {

    private PullToRefreshLayout mPullToRefreshLayout = null;
    private ListView newsListView = null;
    private NewsListAdapter adapter = null;
    private List<NewsBean> items = new ArrayList<NewsBean>();

    private Boolean refreshTag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupUI();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                new InitDataClient().init(MainActivity.this,MainActivity.this, SysConst.Method.TAG_GETNEWS)
                        .constructParams().invoke();*/
/*                new SearchCodesClient().init(MainActivity.this,MainActivity.this,SysConst.Method.TAG_SEARCHCODE)
                        .constructParams("Board").invoke();*/
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupUI(){
        mPullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.ptr_layout);
        newsListView = (ListView) findViewById(R.id.news_list);

        ActionBarPullToRefresh.from(this)
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);

        adapter = new NewsListAdapter(this,items);
        newsListView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_console) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ConsoleActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_wikia) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSuccess(Object tag, JsonElement response) {
        String str_tag = tag.toString();
        if(str_tag.equals(SysConst.Method.TAG_GETNEWS)){
            if(response != null){

                Log.i("response data", response.toString());

                JsonElement test = response;
                JsonArray ja = test.getAsJsonArray();
                for(int i = 0 ; i < ja.size(); i++){
                    JsonObject jo = ja.get(i).getAsJsonObject();
                    NewsBean nb = new NewsBean();
                    nb.setTitle(jo.get("c_title").getAsString());
                    nb.setPic_url(jo.get("c_picture").getAsString());
                    nb.setContent_path(jo.get("c_content_path").getAsString());

                    items.add(nb);
                }

                adapter.notifyDataSetChanged();
            }
        } else if(str_tag.equals(SysConst.Method.TAG_SEARCHCODE)){
            if(response != null){
                List<CodeInfo> codeInfoList = new ArrayList<CodeInfo>();
                JsonElement tempJson = response;
                JsonArray ja = tempJson.getAsJsonArray();
                for(int i = 0; i < ja.size(); i++){
                    JsonObject jo = ja.get(i).getAsJsonObject();
                    CodeInfo ci = new CodeInfo();
                    ci.setC_code_name(jo.get("c_code_name").getAsString());
                    ci.setC_code_value(jo.get("c_code_value").getAsString());

                    codeInfoList.add(ci);
                }
            }
        }

        refreshTag = false;
        mPullToRefreshLayout.setRefreshComplete();
    }

    @Override
    public void onFailed(VolleyError error) {
        refreshTag = false;
        mPullToRefreshLayout.setRefreshComplete();
    }

    @Override
    public void onRefreshStarted(View view) {
        items.clear();
        if(!refreshTag){
            refreshTag = true;
            new GetNewsClient().init(MainActivity.this,MainActivity.this, SysConst.Method.TAG_GETNEWS)
                    .constructParams().invoke();
        }
    }
}
