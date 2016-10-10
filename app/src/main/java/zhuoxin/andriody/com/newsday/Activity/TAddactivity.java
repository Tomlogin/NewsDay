package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

import zhuoxin.andriody.com.newsday.Base.BaseActivity;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;
import zhuoxin.andriody.com.newsday.View.FlowLayout;
import zhuoxin.andriody.com.newsday.interfac.FlowLayoutCallback;

/**
 * Created by Administrator on 9/22 0022.
 */
public class TAddactivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolbar;
    private ListView listview;
    private String[] tabs = {"汽车", "财经", "热点", "社会", "房产", "科技", "影视", "女人"};
    private FlowLayout flowLayout;
    private Set<String> set;
    private List<String> data;
    private boolean needupdate;

    @Override
    public void setcontentLayout() {
        setContentView(R.layout.activity_add);
        toolbar = (Toolbar) findViewById(R.id.addactivity_toolbar);
        listview = (ListView) findViewById(R.id.addactivity_listview);
        listview.setOnItemClickListener(this);
        flowLayout = (FlowLayout) findViewById(R.id.addactivity_flowlayout);

    }

    @Override
    public void initView() {
        toolbar.setNavigationIcon(R.drawable.btn_return);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setBackgroundColor(Color.BLUE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 返回某些数据
                Intent intent = new Intent();
                intent.putExtra("needUpdate",needupdate);
                setResult(101,intent);
                finish();
            }
        });
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, tabs);
        listview.setAdapter(adapter);
        set=Sharepre.gettitlesData(this);
        flowLayout.getSetData(set);
        flowLayout.setCallback(new FlowLayoutCallback() {
            @Override
            public void afterOnChildClick() {
                Toast.makeText(TAddactivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                needupdate = true;
            }
        });
    }

    @Override
    public void afterViewdo() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (set.add(tabs[position])) {// 添加成功
            flowLayout.getSetData(set);
            needupdate = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Sharepre.saveTabInfoToShared(this,set);
    }
}