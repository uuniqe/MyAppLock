package sdf.net.myapplock.ui;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sdf.net.myapplock.R;

/**
 * Created by dingzheng on 2/10/17.
 */
public class CategoryAdapter extends BaseAdapter{
    private PackageManager packageManager;
    private ArrayList<AppInfoParams> mdatas;
    private Context mContext;
    private LayoutInflater mInflater;
    public CategoryAdapter(Context mContext, ArrayList list, PackageManager packageManager) {
        this.mContext = mContext;
        this.packageManager = packageManager;
        this.mdatas = list;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppInfoViewHolder appInfoViewHolder;
        if (convertView == null ){
            convertView = mInflater.inflate(R.layout.applockItem,null);
            appInfoViewHolder = new AppInfoViewHolder();
            appInfoViewHolder.appIcon = (ImageView)convertView.findViewById(R.id.iv_app_icon);
            appInfoViewHolder.label = (TextView) convertView
                    .findViewById(R.id.tv_app_label);
            appInfoViewHolder.checkBox = (CheckBox) convertView
                    .findViewById(R.id.switch_check);
            //首字母
            appInfoViewHolder.letter = (TextView) convertView
                    .findViewById(R.id.tv_letter);
            convertView.setTag(appInfoViewHolder);
        } else {
            appInfoViewHolder = (AppInfoViewHolder) convertView.getTag();
        }

        AppInfoParams appInfoParams = (AppInfoParams) getItem(position);
        appInfoViewHolder.label.setText(appInfoParams.label);
        appInfoViewHolder.appIcon.setImageDrawable(appInfoParams.icon);
        //需要设置checkbox状态


        return convertView;
    }

    public class AppInfoViewHolder{
        ImageView appIcon;
        TextView label;
        TextView letter;
        CheckBox checkBox;
    }
}
