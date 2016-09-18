package com.hoperun.tool;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import com.hoperun.bean.ApplicationInfor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hoperun on 16-9-13.
 */
public class GetApplicationInfo {

    private static final int GET_ALL_APP = 0;
    private static final int GET_THIRD_APP = 1;
    private static final int GET_SYSTEM_APP = 2;
    private static final int GET_SDCARD_APP = 3;

    private Context mContext;
    private PackageManager mPackageManager = null;
    private ArrayList<ApplicationInfor> allAppResult = null;

    public GetApplicationInfo(Context context) {
        mContext = context;
    }

    public ArrayList<ApplicationInfor> getAppInfo() {
        mPackageManager = mContext.getPackageManager();
//        queryAppInfor();
//        queryAllAppInfor();
        allAppResult = queryAllAppByType(GET_THIRD_APP);
        return allAppResult.addAll(queryAppInfor()) == true ? allAppResult : allAppResult;
    }

    private ArrayList<ApplicationInfor> queryAppInfor() {
        ArrayList<ApplicationInfor> allApp = new ArrayList<ApplicationInfor>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //get ResolveInfo
        List<ResolveInfo> resolveInfos = mPackageManager.queryIntentActivities(mainIntent, PackageManager.MATCH_DEFAULT_ONLY);
        //it id very important(sort by name),after this phase,third app will show,if not,only show system app
        Collections.sort(resolveInfos, new ResolveInfo.DisplayNameComparator(mPackageManager));
        if (null != allApp) {
            allApp.clear();
            for (ResolveInfo ri : resolveInfos) {
                String activityName = ri.activityInfo.name;//get launcher application's activityname
                String pckName = ri.activityInfo.packageName;//
                String appLabel = (String) ri.loadLabel(mPackageManager);
                Drawable appIcon = ri.loadIcon(mPackageManager);
                ApplicationInfor ai = new ApplicationInfor();
                ai.setAppIcon(appIcon);
                ai.setAppLabel(appLabel);
                ai.setRunningMem(0);
                //restrict is not ok

                //restrict is not ok
                allApp.add(ai);
            }
        }
        return allApp;
    }

    //this will return all packages installed in device
    private ArrayList<ApplicationInfor> queryAllAppInfor() {
        ArrayList<ApplicationInfor> allApp = new ArrayList<ApplicationInfor>();
        List<PackageInfo> packages = mPackageManager.getInstalledPackages(0);
        for (PackageInfo pi : packages) {
//            if((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
//            {
            String appLabel = (String) pi.applicationInfo.loadLabel(mPackageManager);
            Drawable appIcon = pi.applicationInfo.loadIcon(mPackageManager);
            ApplicationInfor ai = new ApplicationInfor();
            ai.setAppIcon(appIcon);
            ai.setAppLabel(appLabel);
            ai.setRunningMem(0);
            allApp.add(ai);
//                Collections.sort(allApp, new ResolveInfo.DisplayNameComparator(mPackageManager));
//            }
        }
        return allApp;
    }

    //query packages by type
    private ArrayList<ApplicationInfor> queryAllAppByType(int type) {
        ArrayList<ApplicationInfor> allApp = new ArrayList<ApplicationInfor>();
        List<ApplicationInfo> applicationInfos = mPackageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfos, new ApplicationInfo.DisplayNameComparator(mPackageManager));
        switch (type) {
            case GET_ALL_APP:
                for (ApplicationInfo aInfo : applicationInfos) {
                    useInfo(aInfo, allApp);
                }
                break;
            case GET_SYSTEM_APP:
                for (ApplicationInfo aInfo : applicationInfos) {
                    if ((aInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        useInfo(aInfo, allApp);
                    }
                }
                break;
            case GET_THIRD_APP:
                for (ApplicationInfo aInfo : applicationInfos) {
                    if ((aInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        useInfo(aInfo, allApp);
                    } else if ((aInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        useInfo(aInfo, allApp);
                    }
                }
                break;
            case GET_SDCARD_APP:
                for (ApplicationInfo aInfo : applicationInfos) {
                    if (aInfo.flags == ApplicationInfo.FLAG_SYSTEM) {
                        useInfo(aInfo, allApp);
                    }
                }
                break;
            default:
                break;
        }
        return allApp;
    }

    private void useInfo(ApplicationInfo aInfo, ArrayList<ApplicationInfor> allApp) {
        String appLabel = aInfo.loadLabel(mPackageManager).toString();
        Drawable appIcon = aInfo.loadIcon(mPackageManager);
        ApplicationInfor ai = new ApplicationInfor();
        ai.setAppIcon(appIcon);
        ai.setAppLabel(appLabel);
        ai.setRunningMem(0);
        allApp.add(ai);
    }

}
