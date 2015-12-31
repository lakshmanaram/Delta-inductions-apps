package ashmaps.android.ashwin.ashmaps;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText et;
    Button xplore;
    String city=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xplore = (Button)findViewById(R.id.but);
        xplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et = (EditText)findViewById(R.id.city);
                city = et.getText().toString();
                Intent i= new Intent(MainActivity.this,map1.class);
                i.putExtra("cname",city);
                startActivity(i);
                //Toast.makeText(getApplicationContext(),city,Toast.LENGTH_LONG).show();
            }

        });
    }



}
