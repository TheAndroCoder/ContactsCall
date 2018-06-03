package com.example.sachinrana.contactscall;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by Sachin Rana on 27-03-2018.
 */

public class RateAppDialog extends DialogFragment {
RatingBar rb1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.rate_custom,null);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        rb1 =  view.findViewById(R.id.star5);
        Button b1 = view.findViewById(R.id.dismiss1);
        Button b2 = view.findViewById(R.id.ok);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Rated :"+rb1.getRating(), Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getDialog().dismiss();
            }
        });
    }



}
