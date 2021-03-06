package com.virtualstudios.medistore.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.virtualstudios.medistore.R;
import com.virtualstudios.medistore.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               if (Constants.getSPreferences(SplashActivity.this).getLoginStatus()){
                   startActivity(new Intent(SplashActivity.this, MainActivity.class));
               }else {
                   startActivity(new Intent(SplashActivity.this, LoginActivity.class));
               }
                finish();
            }
        }, 1000);
    }
}