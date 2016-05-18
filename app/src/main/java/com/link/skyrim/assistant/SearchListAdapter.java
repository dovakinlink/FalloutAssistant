package com.link.skyrim.assistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.link.skyrim.assistant.bean.CodeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inven on 2016/3/10.
 */
public class SearchListAdapter extends BaseAdapter{

    private Context mContext = null;
    private List<CodeInfo> items = new ArrayList<CodeInfo>();

    public SearchListAdapter(Context context,List<CodeInfo> items){
        this.mContext = context;
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
        ViewHolder holder = null;
        if(convertView == null){
            convertView
                    = LayoutInflater.from(mContext).inflate(R.layout.items_list_cardview_search,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setName(items.get(position).getC_code_name());
        holder.setId(items.get(position).getC_code_value());
        holder.setNote(items.get(position).getC_code_des());
        return convertView;
    }

    public class ViewHolder{
        public TextView name;
        public TextView id;
        public TextView note;

        public ViewHolder(View convertView){
            name = (TextView) convertView.findViewById(R.id.txt_name);
            id = (TextView) convertView.findViewById(R.id.txt_id);
            note = (TextView) convertView.findViewById(R.id.txt_note);
        }

        public void setName(String str) {
            name.setText(str);
        }

        public void setId(String str) {
            id.setText(str);
        }

        public void setNote(String str) {
            note.setText(str);
        }
    }
}
