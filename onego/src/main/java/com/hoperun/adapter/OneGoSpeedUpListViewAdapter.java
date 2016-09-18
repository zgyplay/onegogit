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
public class OneGoSpeedUpListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    private String[] titleName = {"任务管理", "智能加速", "应用限制", "垃圾清理"};
    private String[] summaryName = {"几个待清理任务", "智能结束缓存和后台进程", "禁止软件自行启动", "清理手机中的垃圾文件"};
    private int count = 4;

    public OneGoSpeedUpListViewAdapter(Context context) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.speedup_listview_item, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.listview_item_title);
            viewHolder.summary = (TextView) convertView.findViewById(R.id.listview_item_summary);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText(titleName[position]);
        viewHolder.summary.setText(summaryName[position]);
        return convertView;
    }

    private static class ViewHolder {
        public TextView title;
        public TextView summary;
    }

}
