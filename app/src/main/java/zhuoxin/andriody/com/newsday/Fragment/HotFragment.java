package zhuoxin.andriody.com.newsday.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import zhuoxin.andriody.com.newsday.R;

/**
 * Created by Administrator on 9/21 0021.
 */
public class HotFragment extends Fragment {
    private WebView webView;
    private ImageView img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hot,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView= (WebView) view.findViewById(R.id.web_hot);
        webView.loadUrl("https://www.baidu.com/?tn=02049043_42_pg&ch=7");
        img= (ImageView) view.findViewById(R.id.hot_img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
    }



}
