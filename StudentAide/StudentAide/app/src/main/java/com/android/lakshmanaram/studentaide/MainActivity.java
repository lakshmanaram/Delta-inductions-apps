package com.android.lakshmanaram.studentaide;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity{

    SemDbAdapter semDbAdapter;
    ArrayList<String> subject_names = new ArrayList<>();
    ArrayList<String> subject_codes = new ArrayList<>();
    ArrayList<Integer> min_percent = new ArrayList<>();
    ArrayList<Integer> tot_classes = new ArrayList<>();
    ArrayList<Integer> classes_attended = new ArrayList<>();
    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        semDbAdapter = new SemDbAdapter(getApplicationContext());
    gv = (GridView)findViewById(R.id.gridView);
            semDbAdapter.GetAllData();
            subject_names = semDbAdapter.getSubnames();
            subject_codes = semDbAdapter.getSubcodes();
            min_percent = semDbAdapter.getPercentmin();
            //tot_classes = semDbAdapter.getTcs();
            //classes_attended = semDbAdapter.getCas();
            gv.setAdapter(new customadap(getApplicationContext(), subject_names, subject_codes, min_percent));//,classes_attended,tot_classes));
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("hel",""+position);
                    Intent intent = new Intent(MainActivity.this, ActualActivity.class);
                    intent.putExtra("sub",subject_names.get(position));
                    intent.putExtra("code",subject_codes.get(position));
                    intent.putExtra("min",min_percent.get(position));
                    //intent.putExtra("tcs",tot_classes.get(position));
                    //intent.putExtra("cas",classes_attended.get(position));
                    startActivity(intent);
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent i = new Intent(MainActivity.this,add_screen.class);
            startActivity(i);
            finish();
            return true;
        }
        else if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
