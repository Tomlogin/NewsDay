package zhuoxin.andriody.com.newsday.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.util.List;

import zhuoxin.andriody.com.newsday.Activity.CollectActivity;
import zhuoxin.andriody.com.newsday.info.WebInfo;

/**
 * Created by Administrator on 9/21 0021.
 */
public class Dialogshow {
   public static void dialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
       builder.setMessage("确认删除吗?");
       builder.setTitle("提示");
       builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
       builder.create().show();
   }

}
