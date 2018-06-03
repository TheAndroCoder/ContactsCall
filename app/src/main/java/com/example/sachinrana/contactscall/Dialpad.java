package com.example.sachinrana.contactscall;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dialpad extends AppCompatActivity {
    CardView dialer;
    FrameLayout frame;
    ListView mostusedlist;
    FloatingActionButton fab2;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnstar,btnhash;
    private ImageButton addtocontacts,backspace;
    EditText enternumber;
    StringBuffer sb ;
    MostUsedAdapter mostUsedAdapter;
    ArrayList<DataModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState)throws SecurityException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialpad);
        frame=(FrameLayout) findViewById(R.id.framelayout);
        mostusedlist=(ListView) findViewById(R.id.mostused);
        dialer=(CardView) findViewById(R.id.dialercardview);
        fab2=( FloatingActionButton) findViewById(R.id.fab2);
        btn1=(Button) findViewById(R.id.btn1);
        btn0=(Button) findViewById(R.id.btn0);
        btn2=(Button) findViewById(R.id.btn2);
        btn3=(Button) findViewById(R.id.btn3);
        btn4=(Button) findViewById(R.id.btn4);
        btn5=(Button) findViewById(R.id.btn5);
        btn6=(Button) findViewById(R.id.btn6);
        btn7=(Button) findViewById(R.id.btn7);
        btn8=(Button) findViewById(R.id.btn8);
        btn9=(Button) findViewById(R.id.btn9);
         sb = new StringBuffer();

        enternumber=(EditText) findViewById(R.id.enternumber);
        btnstar=(Button) findViewById(R.id.btnstar);
        btnhash=(Button) findViewById(R.id.btnhash);
        addtocontacts=(ImageButton) findViewById(R.id.addtocontacts);
        backspace=(ImageButton) findViewById(R.id.backspace);
        list=new ArrayList<DataModel>();
       new MyTask().execute();
      fab2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              recreate();
          }
      });


       if(TextUtils.isEmpty(enternumber.getText())){
           backspace.setEnabled(false);
           addtocontacts.setEnabled(false);
       }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
               sb.append("1");
                enternumber.setText(sb.toString());

            }
        });
        addtocontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(enternumber.getText()))
                    Toast.makeText(Dialpad.this, "chal raha h !", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("2");
                enternumber.setText(sb.toString());

            }
        });
        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                sb.delete(0,sb.length());
                enternumber.setText(sb.toString());
                return false;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("3");
                enternumber.setText(sb.toString());

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("4");
                enternumber.setText(sb.toString());

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("5");
                enternumber.setText(sb.toString());

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("6");
                enternumber.setText(sb.toString());

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("7");
                enternumber.setText(sb.toString());

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("8");
                enternumber.setText(sb.toString());

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("9");
                enternumber.setText(sb.toString());

            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setActivated(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("0");
                enternumber.setText(sb.toString());

            }
        });
        btnstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("*");
                enternumber.setText(sb.toString());

            }
        });
        btnhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backspace.setEnabled(true);
                addtocontacts.setEnabled(true);
                if(!(sb.length()==10))
                sb.append("#");
                enternumber.setText(sb.toString());

            }
        });
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(enternumber.getText()))
                    backspace.setEnabled(false);
                if(!TextUtils.isEmpty(enternumber.getText()))
                sb.deleteCharAt(sb.length()-1);
                enternumber.setText(sb.toString());
            }
        });
    }
    public String getContactName(Context con, String Phonenum){

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
    public void removeview(View v){
        frame.removeAllViewsInLayout();
    }
    class MyTask extends AsyncTask<Void,Void,String>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Void... params)throws SecurityException {
            Cursor managedcur = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null,null,null,null);
            int num = managedcur.getColumnIndex(CallLog.Calls.NUMBER);
            String phnnoprev = "";
            while(managedcur.moveToNext()){
                String phnno = managedcur.getString(num);
                if(!(phnno.equals(phnnoprev)))
                    list.add(new DataModel(getContactName(getApplicationContext(),phnno),phnno));
                phnnoprev=phnno;
            }
            mostUsedAdapter= new MostUsedAdapter(getApplicationContext(),R.layout.mostused_custom,list);
            return "All added";
        }

        @Override
        protected void onPostExecute(String aVoid) {
            mostusedlist.setAdapter(mostUsedAdapter);

        }
    }
    public void vidcall(View view){
        View v = findViewById(android.R.id.content);
        Snackbar snackbar=Snackbar.make(v,"Video Call not supported by operator",Snackbar.LENGTH_LONG);
        View sbview = snackbar.getView();
        TextView tv = (TextView) sbview.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.GREEN);
        snackbar.show();
    }
    public void sim1call(View v)throws SecurityException{
        String no = enternumber.getText().toString();
        if(no.charAt(no.length()-1)=='#'){
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+Uri.encode(no))));
        }else{
        Uri callno = Uri.parse("tel:"+no);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(callno);
        startActivity(callIntent);
    }}
    public void sim2call(View v)throws SecurityException{
        String no = enternumber.getText().toString();
        Uri callno = Uri.parse("tel:"+no);
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(callno);
        startActivity(callIntent);
    }
}
