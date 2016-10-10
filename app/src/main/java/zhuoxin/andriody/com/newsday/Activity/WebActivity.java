package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import zhuoxin.andriody.com.newsday.Base.BaseActivity;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Webutil;

public class WebActivity extends BaseActivity {
    private CoordinatorLayout coordinatorLayout;
    private WebView web;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private String url = "";
    private String title="";
//       coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinator);
//        Snackbar snackbar=Snackbar.make(coordinatorLayout,"点我",Snackbar.LENGTH_INDEFINITE);
//        snackbar.getView().setBackgroundColor(Color.WHITE);
//        snackbar.setAction("来啊", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(WebActivity.this,"haole",Toast.LENGTH_LONG).show();
//            }
//        });


    @Override
    public void setcontentLayout() {
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        url = intent.getExtras().getString("url");
        title=intent.getExtras().getString("title");
    }

    private void inittoolbar() {
        toolbar.setTitle("NewsDay");
        toolbar.setNavigationIcon(R.drawable.btn_return);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //toolbar右边的图片的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(Webutil.isdata(WebActivity.this,title,url)){
                 //判断是否已经存在
                }else{
                    Webutil.insertWebInfo(WebActivity.this,title,url);
                }
                Snackbar snackbar = Snackbar.make(toolbar.getRootView(), "收藏成功", Snackbar.LENGTH_SHORT);
                snackbar.setAction("点击查看", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                     Intent intent=new Intent(WebActivity.this,CollectActivity.class);
                        startActivity(intent);
                    }
                }
                ).setActionTextColor(Color.RED).getView().setBackgroundColor(Color.GRAY);
                snackbar.show();
                return true;
            }
        });
    }

    @Override
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.web_toolbar);
        inittoolbar();
        web = (WebView) findViewById(R.id.wed);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }
//toolbar左边的返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web.canGoBack()) {
                web.goBack();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
//toolbar和menu的链接
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void afterViewdo() {
        web.setWebViewClient(new WebViewClient());//设置本页显示
        web.setWebChromeClient(new Mywebviewclient());//设置进度监听
        web.loadUrl(url);
    }
//进度条的监听
    class Mywebviewclient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100)
                progressBar.setVisibility(View.GONE);
        }

    }


}
