package sdf.net.myapplock;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sdf.net.myapplock.ui.AppInfoParams;

public class AppLockMainActivity extends AppCompatActivity {

    private PackageManager localPackageManager;
    ArrayList<AppInfoParams> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_lock_main);
        initView();
        //启动task加载所有app
        //显示到list当中

    }
    private void initView() {
        localPackageManager = this.getPackageManager();
    }
    //启动task加载所有app
    private void loadAllAppsTask (){
        LoadAppTask task = new LoadAppTask();
        task.execute();
    }

    class LoadAppTask extends AsyncTask<String,Void,ArrayList<AppInfoParams>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<AppInfoParams> doInBackground(String... params) {
            Intent localIntent = new Intent("android.intent.action.MAIN", null);
            localIntent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> mAllAppList = localPackageManager.queryIntentActivities(localIntent, 0);
            Collections.sort(mAllAppList, new ResolveInfo.DisplayNameComparator(localPackageManager));
            mList = new ArrayList<AppInfoParams>();
            for (int i=0 ;i< mAllAppList.size();i++){
                ResolveInfo appInfo = (ResolveInfo) mAllAppList.get(i);
                AppInfoParams appInfoParams = new AppInfoParams();
                appInfoParams.icon = appInfo.loadIcon(getPackageManager());
                appInfoParams.label = appInfo.loadLabel(getPackageManager()).toString();
                appInfoParams.packageName = appInfo.activityInfo.packageName;
                mList.add(appInfoParams);
            }
            return mList;
        }

        @Override
        protected void onPostExecute(ArrayList<AppInfoParams> result) {

        }



    }



}
