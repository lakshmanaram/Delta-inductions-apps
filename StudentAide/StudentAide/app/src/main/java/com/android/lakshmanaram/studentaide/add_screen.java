package com.android.lakshmanaram.studentaide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class add_screen extends ActionBarActivity {

    SemDbAdapter semDbAdapter;
    Button bt;
    String subname,subcode;
    Integer submin,mon,tue,wed,thu,fri,sat,sun;
    EditText subnameet,subcodeet, subminet, monet,tueet,wedet,thuet,friet,satet,sunet;
    CheckBox moncb,tuecb,wedcb,thucb,fricb,satcb,suncb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        subnameet = (EditText) findViewById(R.id.sub_name_et);
        subcodeet = (EditText) findViewById(R.id.sub_code_et);
        subminet = (EditText) findViewById(R.id.sub_min_et);
        moncb = (CheckBox) findViewById(R.id.mon_cb);
        monet = (EditText) findViewById(R.id.mon_et);
        tuecb = (CheckBox) findViewById(R.id.tue_cb);
        tueet = (EditText) findViewById(R.id.tue_et);
        wedcb = (CheckBox) findViewById(R.id.wed_cb);
        wedet = (EditText) findViewById(R.id.wed_et);
        thucb = (CheckBox) findViewById(R.id.thu_cb);
        thuet = (EditText) findViewById(R.id.thu_et);
        fricb = (CheckBox) findViewById(R.id.fri_cb);
        friet = (EditText) findViewById(R.id.fri_et);
        satcb = (CheckBox) findViewById(R.id.sat_cb);
        satet = (EditText) findViewById(R.id.sat_et);
        suncb = (CheckBox) findViewById(R.id.sun_cb);
        sunet = (EditText) findViewById(R.id.sun_et);
        bt = (Button) findViewById(R.id.ebutton);
        if(b!=null)
        {
            bt.setText("EDIT SUBJECT");
        }
        else
        {
            bt.setText("ADD SUBJECT");
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subname= subnameet.getText().toString();
                subcode = subcodeet.getText().toString();
                submin = Integer.parseInt(subminet.getText().toString());
                mon = Integer.parseInt(monet.getText().toString());
                tue = Integer.parseInt(tueet.getText().toString());
                wed = Integer.parseInt(wedet.getText().toString());
                thu = Integer.parseInt(thuet.getText().toString());
                fri = Integer.parseInt(friet.getText().toString());
                sat = Integer.parseInt(satet.getText().toString());
                sun = Integer.parseInt(sunet.getText().toString());

                semDbAdapter = new SemDbAdapter(getApplicationContext());
                semDbAdapter.add_data(subname,subcode,submin,mon,tue,wed,thu,fri,sat,sun);

                Intent intent = new Intent(add_screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
