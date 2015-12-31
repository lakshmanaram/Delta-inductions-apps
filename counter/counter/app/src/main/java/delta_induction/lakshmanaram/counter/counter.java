package delta_induction.lakshmanaram.counter;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class counter extends ActionBarActivity {
    int i=0;
    Button button;
    Button reset;
    TextView t;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new increment());
        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener( new OnClickListener() {   //anonymous inner class
            @Override
            public void onClick(View v) {
                i=0;
             //   Log.d("hey","reset");
                t.setText(""+i);
            }
        });
        t=(TextView)findViewById(R.id.textView);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("i",i);
        Log.d("hey","saved");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("hey","restore");t.setText("" + i);
        i=savedInstanceState.getInt("i");
        t.setText(""+i);
    }
    class increment implements OnClickListener{         //inner class

        @Override
        public void onClick(View v) {
            i++;
          //  Log.d("hey","button clicked");
            t.setText(""+i);
            /*
            Toast toast = Toast.makeText(getApplicationContext(), "incremented the counter value",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            */
            /*
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);

            LayoutInflater inflater = getLayoutInflater();
            View appearance = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toast));
            toast.setView(appearance);
            toast.show();
            */
        }
    }
}
