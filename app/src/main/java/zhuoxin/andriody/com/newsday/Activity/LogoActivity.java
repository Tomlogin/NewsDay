package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;

public class LogoActivity extends AppCompatActivity {
    private ImageView img;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        img = (ImageView) findViewById(R.id.logo_img);
        if (Sharepre.getsharepre(this)) {
            Intent intent = new Intent(this, LeadActivity.class);
            startActivity(intent);
            finish();
        }
        animation = AnimationUtils.loadAnimation(this, R.anim.logo);
        img.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //// TODO: 8/31 0031 跳转
                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
