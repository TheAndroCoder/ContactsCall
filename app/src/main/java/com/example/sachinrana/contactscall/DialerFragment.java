package com.example.sachinrana.contactscall;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialerFragment extends Fragment {



    FloatingActionButton fab;
    ArrayList<DataModel> list;
    public DialerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) throws SecurityException {
        // Inflate the layout for this fragment
       final View root = inflater.inflate(R.layout.fragment_dialer,container,false);
        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CONTACTS},2);
        }
        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CALL_PHONE},3);
        }
        ListView listview = (ListView) root.findViewById(R.id.callloglist);
        fab=(FloatingActionButton) root.findViewById(R.id.fab);
        list=new ArrayList<DataModel>();


        Cursor managed = getActivity().getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
        int number = managed.getColumnIndex(CallLog.Calls.NUMBER);

        while(managed.moveToNext()){
            String phnno = managed.getString(number);
            String calltime = managed.getString(managed.getColumnIndex(CallLog.Calls.DURATION));
            list.add(new DataModel(getContactName(getActivity().getApplicationContext(),phnno),phnno,calltime));
        }
        final CustomAdapterLog adapter = new CustomAdapterLog(getActivity().getApplicationContext(),R.layout.calllog_custom,list);
        listview.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),Dialpad.class));
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = adapter.getItem(position).toString();
                Toast.makeText(getActivity().getApplicationContext(), a, Toast.LENGTH_SHORT).show();
            }
        });
  return root;
    }
    public String getContactName(Context con,String Phonenum){

        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(Phonenum));
        Cursor cursor = con.getContentResolver().query(uri,new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME},null,null,null);
        String contactname = null;
        if(cursor==null)
            return null;
        if(cursor.moveToFirst()){
            contactname = cursor.getString(0);
        }
        return contactname;
    }

}
