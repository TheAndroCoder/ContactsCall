package com.example.sachinrana.contactscall;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class FirstWelcome extends AppCompatActivity {
    ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_welcome);
        imgview=( ImageView) findViewById(R.id.first);
       if(ContextCompat.checkSelfPermission(FirstWelcome.this,Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(FirstWelcome.this,new String[]{Manifest.permission.READ_CALL_LOG},1);
       }
        if(ContextCompat.checkSelfPermission(FirstWelcome.this,Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FirstWelcome.this,new String[]{Manifest.permission.READ_CONTACTS},2);
        }
        if(ContextCompat.checkSelfPermission(FirstWelcome.this,Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(FirstWelcome.this,new String[]{Manifest.permission.CALL_PHONE},3);
        }


       final Animation anim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_anim);



        imgview.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(),FragmentActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
