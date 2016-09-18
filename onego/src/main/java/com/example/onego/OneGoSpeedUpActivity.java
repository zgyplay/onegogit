package com.example.onego;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hoperun.adapter.OneGoSpeedUpListViewAdapter;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by hoperun on 16-9-12.
 */
public class OneGoSpeedUpActivity extends Activity {

    private TextView navigationBarTitle = null;
    private TextView memoryinfo = null;
    private ImageView navigationBarBack = null;
    private ListView speedUpListView = null;
    private Button oneKeyClear = null;
    private Context mContext = OneGoSpeedUpActivity.this;
    ActivityManager.MemoryInfo mMemoryInfo = null;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    memoryinfo.setText("MemoryInfo:" + ((MemInfor) msg.obj).getUsedMem() + "GB/" +
                            ((MemInfor) msg.obj).getTotalMem() + "GB");
                    Log.i("ABM", ((MemInfor) msg.obj).getUsedMem());
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_go_speedup);

        navigationBarTitle = (TextView) findViewById(R.id.navigation_top_2);
        navigationBarTitle.setText("手机加速");

        oneKeyClear = (Button) findViewById(R.id.oneKeyClear);
        oneKeyClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ABM", "oneKeyClear go");
                clearMemory();
            }
        });

        navigationBarBack = (ImageView) findViewById(R.id.navigation_top_1);
        navigationBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneGoSpeedUpActivity.this.finish();
            }
        });

        speedUpListView = (ListView) findViewById(R.id.speedup_listView);
        speedUpListView.setAdapter(new OneGoSpeedUpListViewAdapter(mContext));
        speedUpListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.RESTRICT");
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });

//        getMemoryInfo();
        memoryinfo = (TextView) findViewById(R.id.memory_info);
//        memoryinfo.setText("MemoryInfo:" +
//                String .format("%.2f", (mMemoryInfo.totalMem - mMemoryInfo.availMem)/1024f/1024f/1024f) + "GB/" +
//                String .format("%.2f", mMemoryInfo.totalMem/1024f/1024f/1024f) + "GB");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getMemoryInfo();
                    Message msg = new Message();
                    msg.what = 1;
                    MemInfor mi = new MemInfor();
                    mi.setUsedMem(String.format("%.2f", (mMemoryInfo.totalMem - mMemoryInfo.availMem) / 1024f / 1024f / 1024f));
                    mi.setTotalMem(String.format("%.2f", mMemoryInfo.totalMem / 1024f / 1024f / 1024f));
                    msg.obj = mi;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void getMemoryInfo() {
        mMemoryInfo = new ActivityManager.MemoryInfo();
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        mActivityManager.getMemoryInfo(mMemoryInfo);
        Log.i("ABM", "MemoryInfo = " + mMemoryInfo);
        Log.i("ABM", "availMem = " + mMemoryInfo.availMem / 1024f / 1024f / 1024f + "G");
        Log.i("ABM", "totalMem = " + mMemoryInfo.totalMem / 1024f / 1024f / 1024f + "G");
        Log.i("ABM", "useMem = " + numFormat(mMemoryInfo.availMem, mMemoryInfo.totalMem));
    }

    private void clearMemory() {
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = mActivityManager.getRunningAppProcesses();
        if (null != runningAppProcesses) {
            for (int i = 0; i < runningAppProcesses.size(); i++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                Log.i("ABM", "i = " + i + " pid = " + runningAppProcessInfo.pid);
                Log.i("ABM", "i = " + i + " processName = " + runningAppProcessInfo.processName);
                Log.i("ABM", "i = " + i + " importance = " + runningAppProcessInfo.importance);
                String[] pkgList = runningAppProcessInfo.pkgList;

                if (runningAppProcessInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    for (int j = 0; j < pkgList.length; j++) {
                        Log.i("ABM", "packageName = " + pkgList[j]);
                        mActivityManager.killBackgroundProcesses(pkgList[j]);
                    }
                }
            }
        }
    }

    // format number,get memory information
    private String numFormat(double num1, double num2) {
        String result = null;
        NumberFormat nt = NumberFormat.getPercentInstance();
        nt.setMaximumFractionDigits(0);
        result = nt.format(1 - num1 / num2);
        return result;
    }

    class MemInfor {
        public String usedMem;
        public String totalMem;

        public MemInfor() {
        }

        public MemInfor(String usedMem, String totalMem) {
            this.usedMem = usedMem;
            this.totalMem = totalMem;
        }

        public void setUsedMem(String usedMem) {
            this.usedMem = usedMem;
        }

        public void setTotalMem(String totalMem) {
            this.totalMem = totalMem;
        }

        public String getUsedMem() {
            return usedMem;
        }

        public String getTotalMem() {
            return totalMem;
        }

        @Override
        public String toString() {
            return "MemInfor{" +
                    "usedMem='" + usedMem + '\'' +
                    ", totalMem='" + totalMem + '\'' +
                    '}';
        }
    }
}

