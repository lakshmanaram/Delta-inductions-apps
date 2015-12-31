package com.android.lakshmanaram.studentaide;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ActualActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);
        caldroidFragment.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date1 = sdf.format(date);

                try {
                    FragmentManager fm = getFragmentManager();
                    Bundle args= new Bundle();
                    args.putString("date", date.toString());
                    day_dialog dd = new day_dialog();
                    dd.setArguments(args);
                    dd.show(fm, "daycount");
                    Log.d("hel",Datacls.getPresent()+":"+Datacls.getAbsent()+":"+Datacls.isClose());
                } catch (Exception e) {
                    Log.d("hel",e.toString());
                }
            }
        });
        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar, caldroidFragment);
        t.commit();

        Log.d("hel",b.get("sub").toString());
    }
}
