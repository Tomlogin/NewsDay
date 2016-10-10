package zhuoxin.andriody.com.newsday.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 8/31 0031.
 */
public class Sharepre {
    private static final String filename = "sharedPreferences";
    private static final String titlefilename = "titlemenue";
    private static final String key = "KEY";
    private static final String titlekey = "menue";
    private static SharedPreferences sharedPreferences;
    private static Set<String> titles;
    private static List<String>titlelist;
    //获取sharedPreferences
    public static boolean getsharepre(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(key, true);
    }

    //写入sharedPreferences
    public static void writesharedPreferences(Context context, boolean isfirst) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
        editor.putBoolean(key, isfirst);
        editor.commit();
    }

    //获取titles的初始化数据
    public static Set<String> gettitlesData(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(titlefilename, Context.MODE_PRIVATE);
        }
        Set<String> titles=sharedPreferences.getStringSet(titlekey,null);
//        MyLog.e("获取titleset"+titles.toString());
        if (titles == null) {
            titles = new TreeSet<>();
            titles.add("汽车");
            titles.add("财经");
            titles.add("热点");
            titles.add("房产");
            titles.add("科技");
            titles.add("社会");
//            MyLog.e(1+":"+titles.toString());
        }
        return titles;
    }
//获取titles的初始化数据d的list集合
    public static List<String> gettitlesListData(Context context) {
        List<String> titlelist=new ArrayList<>();
        Set<String> sets=gettitlesData(context);
        for (String temp:sets) {
            titlelist.add(temp);
        }
        return  titlelist;
    }
    //写入titles的数据
    public static void writetitlesData(Context context, Set<String> titles) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(titlefilename, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
        editor.putStringSet(titlekey,titles);
//        MyLog.e("写入"+titles.toString());
        editor.commit();
    }
//添加新的数据信息
//public static void writetitlesData(Context context,Set<String> tabs){
//    sharedPreferences=context.getSharedPreferences(titlefilename,Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sharedPreferences.edit();
//    editor.clear();
//    editor.putStringSet("titlekey",tabs);
//    editor.commit();
//}
public static void saveTabInfoToShared(Context context,Set<String> tabs){
    SharedPreferences sharedPreferences = context.getSharedPreferences(titlefilename,Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putStringSet(titlekey,tabs);
    editor.commit();
}
}