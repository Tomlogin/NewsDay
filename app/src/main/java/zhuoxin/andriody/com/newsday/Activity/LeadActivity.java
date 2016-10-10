package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;

public class LeadActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{
private ViewPager viewPager;
    private Button button;
    private List<View>viewlist;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        viewPager=(ViewPager) findViewById(R.id.lead_viewpager);
        button=(Button)findViewById(R.id.lead_button);
         initData();
        viewPager.setAdapter(adaoter);
        viewPager.addOnPageChangeListener(this);
        button.setOnClickListener(this);
    }

    private void initData() {
        viewlist=new ArrayList<>();
        img =new ImageView(this);
        img.setBackgroundResource(R.drawable.lead_1);
        viewlist.add(img);
        img =new ImageView(this);
        img.setBackgroundResource(R.drawable.lead_2);
        viewlist.add(img);
        img =new ImageView(this);
        img.setBackgroundResource(R.drawable.lead_3);
        viewlist.add(img);

    }

    PagerAdapter adaoter=new PagerAdapter() {
        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(viewlist.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewlist.get(position),0);
            return viewlist.get(position);
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==2){
            button.startAnimation(AnimationUtils.loadAnimation(this,R.anim.logo));
            button.setVisibility(View.VISIBLE);
        }else{
            button.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lead_button:
                Sharepre.writesharedPreferences(this,false);
                Intent intent=new Intent(this,LogoActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
