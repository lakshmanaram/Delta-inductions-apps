package ashmaps.android.ashwin.ashmaps;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class map1 extends ActionBarActivity {

    String newcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map1);

        Intent iint = getIntent();
        Bundle b = iint.getExtras();
        if(b!=null)
        {
            newcity = (String)b.get("cname");
        }
        Toast.makeText(getApplicationContext(),"****"+newcity+"****",Toast.LENGTH_LONG).show();
    }



}
