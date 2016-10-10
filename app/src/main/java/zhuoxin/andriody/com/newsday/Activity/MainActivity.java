package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import zhuoxin.andriody.com.newsday.Adapter.MainFragmentAdapter;
import zhuoxin.andriody.com.newsday.Base.BaseActivity;
import zhuoxin.andriody.com.newsday.Fragment.FragmentInfo;
import zhuoxin.andriody.com.newsday.Fragment.HotFragment;
import zhuoxin.andriody.com.newsday.Fragment.SearchFragment;
import zhuoxin.andriody.com.newsday.R;

/**
 * Created by Administrator on 9/1 0001.
 */
public class MainActivity extends BaseActivity implements View.OnClickListener ,ViewPager.OnPageChangeListener{
    private RadioButton tvone,tvtwo,tvthree;
    private ViewPager viewPager;
    private List<Fragment>fragments;
    private MainFragmentAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton [] a=new RadioButton[3];
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView textView;
    private ImageView img;
    @Override
    public void setcontentLayout() {
          setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        drawerLayout=(DrawerLayout)findViewById(R.id.home_drawerlayout);
        navigationView=(NavigationView)findViewById(R.id.home_NavigationView);
        View view=navigationView.getHeaderView(0);
        img= (ImageView) view.findViewById(R.id.login_img);
        textView= (TextView)view.findViewById(R.id.menu_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DologingActivity.class);
                startActivityForResult(intent,101);
            }
        });
        toolbar=(Toolbar)findViewById(R.id.home_toolbar);
        textView= (TextView)navigationView.findViewById(R.id.menu_text);
        viewPager=(ViewPager)findViewById(R.id.main_viewpager);
        tvone=(RadioButton)findViewById(R.id.tv_one);
        tvone.setOnClickListener(this);
        tvtwo=(RadioButton)findViewById(R.id.tv_two);
        tvtwo.setOnClickListener(this);
        tvthree=(RadioButton)findViewById(R.id.tv_three);
        tvthree.setOnClickListener(this);
        fragments=new ArrayList<>();
        fragments.add(new FragmentInfo() );
        fragments.add(new HotFragment());
        fragments.add(new SearchFragment());
        manager=getSupportFragmentManager();
        adapter=new MainFragmentAdapter(manager,fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        a=new RadioButton[]{tvone,tvtwo,tvthree};
       initToolbar();
    }
    public void initToolbar(){
        toolbar.setTitle("NewsDay");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setLogo(R.drawable.logo);
        toolbar.setNavigationIcon(R.drawable.btn_return);
        setSupportActionBar(toolbar);//设置toolbar
        getSupportActionBar().setHomeButtonEnabled(true);//获取toolbar并设置主界面左边的返回图标是否可用
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_me:
                        // TODO: 9/6 0006 关于我们
                        Toast.makeText(MainActivity.this,"点我就变性",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_star:
                        // TODO: 9/6 0006  我的收藏
                        Intent intent=new Intent(MainActivity.this,CollectActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.drawer_settings:
                        // TODO: 9/6 0006  设置
                        Toast.makeText(MainActivity.this,"点我你怀孕",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public void afterViewdo() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_one:
            viewPager.setCurrentItem(0);
                break;
            case R.id.tv_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_three:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showShare();
        return true;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < a.length; i++) {
            if(i==position)
            {a[i].setChecked(true);}
            else{
                a[i].setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101&&resultCode==100&&data.getExtras().getBoolean("isdologing")){
            img.setImageResource(R.drawable.a000);
            navigationView=(NavigationView)findViewById(R.id.home_NavigationView);
            View view=navigationView.getHeaderView(0);
            textView= (TextView)view.findViewById(R.id.menu_text);
            textView.setClickable(false);
            textView.setText("Tom");
        }
    }
}
