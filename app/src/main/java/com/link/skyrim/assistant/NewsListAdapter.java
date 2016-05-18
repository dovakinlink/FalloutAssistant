package com.link.skyrim.assistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.link.skyrim.assistant.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inven on 2016/3/3.
 */
public class NewsListAdapter extends BaseAdapter {

    private Context mContext = null;
    private List<NewsBean> items = new ArrayList<NewsBean>();

    public NewsListAdapter(Context context, List<NewsBean> items){
        mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext)
                        .inflate(R.layout.news_list_cardview_main, null);
            ViewHolder holder = new ViewHolder();
            holder.testTxt = (TextView) convertView.findViewById(R.id.test_txt);
            holder.picture = (ImageView) convertView.findViewById(R.id.picture);
            holder.testTxt.setText(items.get(position).getTitle());
            Picasso.with(mContext).load(items.get(position).getPic_url())
                    .centerCrop().resize(100,100).into(holder.picture);
            convertView.setTag(holder);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.testTxt.setText(items.get(position).getTitle());
            Picasso.with(mContext).load(items.get(position).getPic_url())
                    .centerCrop().resize(100,100).into(holder.picture);
        }

        return convertView;
    }

    public class ViewHolder{
        public TextView testTxt = null;
        public ImageView picture = null;
    }
}
