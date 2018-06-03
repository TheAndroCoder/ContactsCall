package com.example.sachinrana.contactscall;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sachin Rana on 08-03-2018.
 */

public class MostUsedAdapter extends ArrayAdapter<DataModel> {
    private ArrayList<DataModel> dataset;
    Context con;
    int resource;
    public MostUsedAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<DataModel> dataset) {
        super(context, resource,dataset);
        this.con=context;
        this.resource=resource;
        this.dataset=dataset;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View view = layoutInflater.inflate(R.layout.mostused_custom,null,false);
        TextView mostusedname = (TextView) view.findViewById(R.id.mostusedtextviewname);
        TextView mostusednum = ( TextView) view.findViewById(R.id.mostusedtextviewnumber);
        ImageButton mostusedcall = ( ImageButton) view.findViewById(R.id.mostusedcall);
        DataModel datapos=dataset.get(position);
        mostusedname.setText(datapos.getName());
        mostusednum.setText(datapos.getPhone());

        return view;
    }
}
