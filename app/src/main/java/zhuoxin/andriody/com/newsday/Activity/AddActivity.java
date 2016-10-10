package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import zhuoxin.andriody.com.newsday.Base.BaseActivity;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;
import zhuoxin.andriody.com.newsday.View.FlowLayout;
import zhuoxin.andriody.com.newsday.interfac.FlowLayoutCallback;

public class AddActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private Toolbar toolbar;
    private ListView listview;
    private List<String> list;
    private FlowLayout flowLayout;
    private Set<String> set ;
    private List<String> data;
    private boolean needupdate;


    @Override
    public void setcontentLayout() {
        setContentView(R.layout.activity_add);
        toolbar=(Toolbar)findViewById(R.id.addactivity_toolbar);
        listview=(ListView)findViewById(R.id.addactivity_listview);
        flowLayout=(FlowLayout)findViewById(R.id.addactivity_flowlayout);
    }

    @Override
    public void initView() {
        set= Sharepre.gettitlesData(this);
        data=new ArrayList<String>();
        list=new ArrayList<>();
        list.add("汽车");
        list.add("财经");
        list.add("热点");
        list.add("社会");
        list.add("房产");
        list.add("科技");
        list.add("CBA");
        list.add("体育");
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list));
        listview.setOnItemClickListener(this);
        flowLayout.getSetData(Sharepre.gettitlesData(this));
        setToolbar();
//        flowLayout.setCallback(new FlowLayoutCallback(){
//            @Override
//            public void afterOnChildClick() {
//                Toast.makeText(AddActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
//                needupdate = true;
//            }
//        });

    }

    /**
     *
     */
    public void setToolbar(){
        toolbar.setTitle("NewsDay");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.BLUE);
        toolbar.setNavigationIcon(R.drawable.btn_return);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setResultData();
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putBoolean("1",needupdate);
                intent.putExtras(bundle);
                setResult(1001,intent);
                finish();
            }
        });

    }
    private void setResultData(){
        for (String temp:set) {
            data.add(temp);
        }
        Intent intent=new Intent();
//                intent.putExtra("needUpdate",needupdate);
        Bundle bundle=new Bundle();
//        bundle.putSerializable("1",(Serializable) data);
        bundle.putBoolean("1",needupdate);
        intent.putExtras(bundle);
        setResult(1001,intent);
        Sharepre.writetitlesData(this,set);
        finish();
    }

    @Override
    public void afterViewdo() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(set.add(list.get(position))){
            Sharepre.writetitlesData(this,set);
            flowLayout.getSetData(set);
            needupdate=true;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sharepre.writetitlesData(this,set);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            Sharepre.writetitlesData(this,set);

        }
        return super.onKeyDown(keyCode, event);
    }
}
