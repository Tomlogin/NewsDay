package zhuoxin.andriody.com.newsday.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mob.tools.utils.SQLiteHelper;

/**
 * Created by Administrator on 9/20 0020.
 */
public class MySqlHelper extends SQLiteOpenHelper {


    public MySqlHelper(Context context) {
        super(context, "webtable", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table= "create table webtable(title text,url text)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
