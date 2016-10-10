package zhuoxin.andriody.com.newsday.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import zhuoxin.andriody.com.newsday.Base.BaseActivity;
import zhuoxin.andriody.com.newsday.R;
import zhuoxin.andriody.com.newsday.Util.Sharepre;

public class DologingActivity extends BaseActivity implements View.OnClickListener{
    private EditText admin,pwd;
    private Button loging,cancel;
    private  boolean isdologing;
    @Override
    public void setcontentLayout() {
        setContentView(R.layout.activity_dologing);
    }

    @Override
    public void initView() {
        admin= (EditText) findViewById(R.id.dologing_admin);
        pwd= (EditText) findViewById(R.id.dologing_pwd);
        loging= (Button) findViewById(R.id.loging);
        cancel= (Button) findViewById(R.id.cancel);
    }

    @Override
    public void afterViewdo() {
         loging.setOnClickListener(this);
         cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loging:
                if(admin.getText().toString().equals("279313838")&&pwd.getText().toString().equals("123456")){
                    Toast.makeText(DologingActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    isdologing=true;
                    Intent intent=new Intent();
//                intent.putExtra("needUpdate",needupdate);
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("isdologing",isdologing);
                    intent.putExtras(bundle);
                    setResult(100,intent);
                    finish();
                }
                break;
            case R.id.cancel:
                admin.setText("");
                pwd.setText("");
                break;
        }
    }
}
