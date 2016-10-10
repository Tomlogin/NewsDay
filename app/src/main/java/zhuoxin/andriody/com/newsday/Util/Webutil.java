package zhuoxin.andriody.com.newsday.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.newsday.info.WebInfo;

/**
 * Created by Administrator on 9/20 0020.
 */
public class Webutil {
    static SQLiteDatabase sqLiteDatabase;
   static MySqlHelper mySqlHelper;
    /**
     * 对外接口 收藏网页信息时调用【保存到数据库】
     * @param context
     * @param title 网页的标题
     * @param url 网页的网址
     */
    public static void insertWebInfo(Context context, String title, String url){
        mySqlHelper=new MySqlHelper(context);
        sqLiteDatabase=mySqlHelper.getWritableDatabase();
//        String insertSql = "INSERT INTO web_tb ('title','url') VALUES('"+title+"','"+url+"')";
//        sqLiteDatabase.execSQL(insertSql);
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("url",url);
        sqLiteDatabase.insert("webtable",null,contentValues);
        sqLiteDatabase.close();
    }

    /**
     * 对外接口 从数据库删除数据
     * @param context
     * @param url
     */
    public static void deleteWebInfo(Context context,String url){
        mySqlHelper=new MySqlHelper(context);
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
//        String deleteString = "DELETE FROM web_tb WHERE url=?";
//        sqLiteDatabase.execSQL(deleteString,new String[]{url});
        sqLiteDatabase.delete("webtable","url=?",new String[]{url});
        sqLiteDatabase.close();
    }
    //对外接口，网页收藏界面调用，返回已收藏的页面数据
    public static List<WebInfo> getWebInfoFromSQLite(Context context){
//        LogTool.e("数据库获取数据");
        List<WebInfo> list = new ArrayList<>();
        mySqlHelper=new MySqlHelper(context);
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from webtable",null);
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            list.add(new WebInfo(title,url));
        }
        return list;
    }
//模糊查询
    public static List<WebInfo> getsearch(Context context,String name){
        List<WebInfo> list = new ArrayList<>();
        mySqlHelper=new MySqlHelper(context);
        sqLiteDatabase = mySqlHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from webtable where title like ?",
                new String[]{'%'+name+'%'});
        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            list.add(new WebInfo(title,url));
        }
        return list;
    }
    //数据是否已存在
  public static boolean isdata(Context context,String title,String url){
      List<WebInfo> list = new ArrayList<>();
      list=getWebInfoFromSQLite(context);
      for (int i = 0; i <list.size(); i++) {
          if(title.equals(list.get(i).title)&&url.equals(list.get(i).url)){
              return true;
          }
      }
      return false;
  }
}
