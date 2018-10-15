package l.com.ldk.duykhanh.lab1_khanhpd02377.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import l.com.ldk.duykhanh.lab1_khanhpd02377.Login.LoginActivity;
import l.com.ldk.duykhanh.lab1_khanhpd02377.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new
                        Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },1500);
        //oh
        // trước tiên thêm người dùng đc
        // sau đó ra màn hình login để check
        // thao tác ở database
        // còn nút lưu t gửi file cho
        // login dễ mà
        //tức là làm cái thêm người  dùng được ms làm cái login à
        // thì muốn đăng nhập phải đăng ksy
        //để t làm ok

    }
}
