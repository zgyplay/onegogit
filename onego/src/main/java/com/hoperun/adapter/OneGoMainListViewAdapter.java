package com.hoperun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onego.R;

/**
 * Created by hoperun on 16-9-9.
 */
public class OneGoMainListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    private int[] imageRes = {R.drawable.speedup, R.drawable.software, R.drawable.power,
            R.drawable.safe, R.drawable.flow};
    private String[] titleName = {"手机加速", "软件管理", "节电优化", "安全与隐私流量监控", "流量监控"};
    private int count = 5;

    public OneGoMainListViewAdapter(Context context) {
        mContext = context;
//        imageRes = {mContext.getResources().getDrawable(R.drawable.)};
    }

    @Override
    public int getCount() {
        return count;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.main_listview_item, null);
            viewHolder.header = (ImageView) convertView.findViewById(R.id.listview_item_header);
            viewHolder.title = (TextView) convertView.findViewById(R.id.listview_item_title);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.header.setBackgroundResource(imageRes[position]);
        viewHolder.title.setText(titleName[position]);
        return convertView;
    }

    private static class ViewHolder {
        public ImageView header;
        public TextView title;
    }

}
