package com.example.sachinrana.contactscall;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperAbout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_about);

    }
    public void blogview(View v){
        Intent blogintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.thehackedway.blogspot.in"));
        startActivity(blogintent);
    }
}
