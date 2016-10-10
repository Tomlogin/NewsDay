package zhuoxin.andriody.com.newsday.Base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 8/31 0031.
 */
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setcontentLayout();
        initView();
        afterViewdo();
    }
    public abstract void setcontentLayout();

    public abstract void initView();

    public abstract void afterViewdo();

    /**
     * 获取版本名
     */
    public String getversionName(){
        String versionName="";
        try {
           versionName=getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
    /**
     * 获取版本号
     */
    public String getversionCode(){
        int versionCode=0;
        try {
            versionCode=getPackageManager().getPackageInfo(getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode+"";
    }
    /**
     * 跳转
     */
    public void startActivity(Class<?> agr){
       Intent intent=new Intent(this,agr);
        startActivity(intent);
        finish();
    }

}
