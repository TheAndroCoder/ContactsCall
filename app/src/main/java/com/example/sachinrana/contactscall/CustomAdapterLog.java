package com.example.sachinrana.contactscall;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sachin Rana on 08-02-2018.
 */

public class CustomAdapterLog extends ArrayAdapter<DataModel> {
    private ArrayList<DataModel> dataset;
    Context mcontext;
    int resource;
    Dialog dialog;

    public CustomAdapterLog(@NonNull Context context, @LayoutRes int resource,ArrayList<DataModel> dataset) {
        super(context, resource,dataset);
        this.mcontext=context;
        this.resource=resource;
        this.dataset=dataset;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        final View view=layoutInflater.inflate(R.layout.calllog_custom,null,false);
        final TextView firstletter = (TextView) view.findViewById(R.id.firstletter);
       final TextView Name = (TextView) view.findViewById(R.id.namehere);
        final TextView number = (TextView) view.findViewById(R.id.numberhere);
        TextView calltime = (TextView) view.findViewById(R.id.time);
        ImageButton call = (ImageButton) view.findViewById(R.id.call);

        DataModel datapos=dataset.get(position);
        correctTime(datapos.getTime());

        Name.setText(datapos.getName());
        firstletter.setText(Name.getText().subSequence(0,1));
        calltime.setText(correctTime(datapos.getTime()));
        number.setText(datapos.getPhone());
        dialog = new Dialog(mcontext);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri callno = Uri.parse("tel:"+number.getText().toString().trim());

                Intent intent = new Intent(Intent.ACTION_CALL,callno);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(callno);
                if(ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) mcontext,new String[]{Manifest.permission.CALL_PHONE},1);
                }
                mcontext.startActivity(intent);
            }
        });
       /* firstletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FirstLetterView f = new FirstLetterView();
                Activity activity = (Activity)mcontext;
                FragmentManager manager = ((AppCompatActivity)activity).getSupportFragmentManager();
                f.show(manager,"no tag");

            }
        });*/
        return view;
    }
    public String correctTime(String s){
        int sec = Integer.parseInt(s);
        int min=0,hr=0;
        while(sec>60){
            sec=sec-60;
            min++;
            while(min>60){
                min=min-60;
                hr++;
            }
        }
        if(sec==0)
            return "Duration :"+sec+" sec";
        if(min==0 && hr ==0 && sec!=0)
            return "Duration :"+sec+" sec";
        else if(hr==0 && min!=0 && sec!=0)
            return "Duration :"+min+" min "+sec+" sec";
        return "Duration :"+hr+" hr "+min+" min "+sec+" sec";
    }

}
