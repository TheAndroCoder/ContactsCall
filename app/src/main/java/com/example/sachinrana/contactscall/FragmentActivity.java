package com.example.sachinrana.contactscall;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FragmentActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigation;

    RateAppDialog rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ActivityCompat.requestPermissions(FragmentActivity.this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        ActivityCompat.requestPermissions(FragmentActivity.this,new String[]{Manifest.permission.READ_CONTACTS},2);
        ActivityCompat.requestPermissions(FragmentActivity.this,new String[]{Manifest.permission.CALL_PHONE},3);
        drawerlayout=(DrawerLayout) findViewById(R.id.drawerlayout);
        navigation=(NavigationView) findViewById(R.id.navigation);
        actionBarDrawerToggle=new ActionBarDrawerToggle(FragmentActivity.this,drawerlayout,R.string.Open,R.string.Close);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);

        /*LayoutInflater inflater = this.getLayoutInflater();
        View inflated = inflater.inflate(R.layout.rate_custom,null);
        rb1= inflated.findViewById(R.id.star5);
        dismiss = inflated.findViewById(R.id.dismiss1);*/
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.namecolor));
        toolbar.setBackgroundColor(getResources().getColor(R.color.tabcolor));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle.syncState();
        tabLayout=(TabLayout) findViewById(R.id.tablayout);
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragments(new DialerFragment(),"CALL");
        viewPagerAdapter.AddFragments(new ContactsFragment(),"CONTACTS");
        viewPagerAdapter.AddFragments(new SmsFragment(),"SMS");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id){
                    case R.id.rateus :
                      rate = new RateAppDialog();

                        rate.show(getSupportFragmentManager(),"my");


                        break;
                    case R.id.aboutdev :
                        startActivity(new Intent(FragmentActivity.this,DeveloperAbout.class));
                        break;
                    case R.id.BlockList :
                        Toast.makeText(FragmentActivity.this, "Block List Activity", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Settings :
                        Toast.makeText(FragmentActivity.this, "Settings Activity", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.reportbug :
                        Toast.makeText(FragmentActivity.this, "Report Bug Activity", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.share :
                        //Toast.makeText(FragmentActivity.this, "Share App", Toast.LENGTH_SHORT).show();
                        shareApplication();
                        break;
                    case R.id.faq :
                        Toast.makeText(FragmentActivity.this, "FAQ section", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        int id = item.getItemId();
        switch(id){
            case R.id.delete :
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteall :
                Toast.makeText(this, "Delete all", Toast.LENGTH_SHORT).show();
                break;
            case R.id.appsettings :
                Toast.makeText(this, "App Settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
    private void shareApplication(){
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String Filepath = app.sourceDir;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        File original = new File(Filepath);
        try{
            File temp = new File(getExternalCacheDir()+"/ExtractedApk");
            if(!temp.isDirectory()){
                if(!temp.mkdirs())
                    return;
            }
            temp=new File(temp.getPath()+"/"+getString(app.labelRes).replace(" ","").toLowerCase()+".apk");
            if (!temp.exists()) {
                if (!temp.createNewFile()) {
                    return;
                }
            }
            InputStream in = new FileInputStream(original);
            OutputStream out = new FileOutputStream(temp);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(temp));
            startActivity(Intent.createChooser(intent,"Share App Via"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
