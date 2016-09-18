package com.hoperun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.onego.R;
import com.hoperun.bean.ApplicationInfor;

import java.util.ArrayList;

/**
 * Created by hoperun on 16-9-13.
 */
public class OneGoRestritListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ApplicationInfor> allApp = null;

    public OneGoRestritListViewAdapter(Context context, ArrayList<ApplicationInfor> list) {
        mContext = context;
        allApp = list;
    }

    @Override
    public int getCount() {
        return allApp == null ? 0 : allApp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.restrict_listview_item, null);
            holder.appIcon = (ImageView) convertView.findViewById(R.id.appIcon);
            holder.appLabel = (TextView) convertView.findViewById(R.id.appLabel);
            holder.mSwitch = (Switch) convertView.findViewById(R.id.switchBtn);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.appIcon.setBackground(allApp.get(position).getAppIcon());
        holder.appLabel.setText(allApp.get(position).getAppLabel());
        holder.mSwitch.setChecked(false);
        return convertView;
    }

    private static class ViewHolder {
        public ImageView appIcon;
        public TextView appLabel;
        public Switch mSwitch;
    }
}
