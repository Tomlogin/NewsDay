package zhuoxin.andriody.com.newsday.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import zhuoxin.andriody.com.newsday.Adapter.CollectAdapter;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Webutil;
import zhuoxin.andriody.com.newsday.info.WebInfo;

public class CollectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private CollectAdapter adapter;
    private List<WebInfo> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        listView=(ListView)findViewById(R.id.collect_lv);
        list=Webutil.getWebInfoFromSQLite(this);
        adapter=new CollectAdapter(list,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){

                // TODO: 9/20 0020 长按删除操作
               dialog(CollectActivity.this,position);
                return true;
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// TODO: 9/20 0020 点击打开操作
        Intent intent=new Intent(this, WebActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("url",list.get(position).url);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    //删除dialog
    public  void dialog(Context context,final int postion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确认删除吗?");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Webutil.deleteWebInfo(CollectActivity.this,list.get(postion).url);
                list.remove(postion);
                adapter.notifyDataSetChanged();
                Toast.makeText(CollectActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
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
