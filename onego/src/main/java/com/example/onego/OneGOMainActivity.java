package com.example.onego;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hoperun.adapter.OneGoMainListViewAdapter;

public class OneGOMainActivity extends AppCompatActivity {

    private TextView navigationBarTitle = null;
    private ListView mainListView = null;
    private BaseAdapter mainListViewAdapter = null;
    private Context mContext = OneGOMainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_one_gomain);

        navigationBarTitle = (TextView) findViewById(R.id.navigation_top_2);
        navigationBarTitle.setText("OneGoSafer");

        int id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelOffset(id);
        Log.i("ABM", "System navigationbar height = " + height);

        mainListView = (ListView) findViewById(R.id.main_listView);
        mainListViewAdapter = new OneGoMainListViewAdapter(OneGOMainActivity.this);
        mainListView.setAdapter(mainListViewAdapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SPEEDUP");
                    OneGOMainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
