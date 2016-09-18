package com.example.onego;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hoperun.adapter.OneGoRestritListViewAdapter;
import com.hoperun.bean.ApplicationInfor;
import com.hoperun.tool.GetApplicationInfo;

import java.util.ArrayList;

/**
 * Created by hoperun on 16-9-13.
 */
public class OneGoAppRestrictActivity extends Activity {

    private Context mContext = null;
    private ListView appListView = null;
    private ArrayList<ApplicationInfor> allApp = null;
    private GetApplicationInfo getApplicationInfo = null;
    private LinearLayout navigationTop = null;
    private TextView navigationTitle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restrict_application);
        mContext = OneGoAppRestrictActivity.this;
        getApplicationInfo = new GetApplicationInfo(mContext);
        allApp = getApplicationInfo.getAppInfo();

        navigationTop = (LinearLayout) findViewById(R.id.navigation_top);
        navigationTop.setBackgroundColor(Color.parseColor("#696969"));

        navigationTitle = (TextView) findViewById(R.id.navigation_top_2);
        navigationTitle.setText("应用程序后台限制");

        appListView = (ListView) findViewById(R.id.restrict_listview);
        appListView.setAdapter(new OneGoRestritListViewAdapter(mContext, allApp));
        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }
}
