package tictactoe.ultimate.com.android.lakshmanaram.ultimate_tictactoe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements communicator{

    singlegrid_frag sf1,sf2,sf3,sf4,sf5,sf6,sf7,sf8,sf9;
    View v1;
    Thread v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        v = new Thread(new Validthread());
        Dataclass.initial();
        FragmentManager fm  = getFragmentManager();
        sf1=(singlegrid_frag) fm.findFragmentById(R.id.box1);
        sf2=(singlegrid_frag) fm.findFragmentById(R.id.box2);
        sf3=(singlegrid_frag) fm.findFragmentById(R.id.box3);
        sf4=(singlegrid_frag) fm.findFragmentById(R.id.box4);
        sf5=(singlegrid_frag) fm.findFragmentById(R.id.box5);
        sf6=(singlegrid_frag) fm.findFragmentById(R.id.box6);
        sf7=(singlegrid_frag) fm.findFragmentById(R.id.box7);
        sf8=(singlegrid_frag) fm.findFragmentById(R.id.box8);
        sf9=(singlegrid_frag) fm.findFragmentById(R.id.box9);
    }

    @Override
    public void validate(int cno) {
        new vasync().execute(cno);
        //v.start();
        /*Dataclass.fillcomp();
            Toast.makeText(getApplicationContext(),"Over",Toast.LENGTH_LONG).show();*/
        /*if(Dataclass.iswon()==1)
        {
            Toast.makeText(getApplicationContext(),"Game won by X",Toast.LENGTH_LONG).show();
        }
        else if(Dataclass.iswon()==2)
        {
            Toast.makeText(getApplicationContext(),"Game won by Y",Toast.LENGTH_LONG).show();
        }
        */
    }

    @Override
    public void highlightin(int to) {

        sf1.chhigh(to);
        sf2.chhigh(to);
        sf3.chhigh(to);
        sf4.chhigh(to);
        sf5.chhigh(to);
        sf6.chhigh(to);
        sf7.chhigh(to);
        sf8.chhigh(to);
        sf9.chhigh(to);

    }
    private class vasync extends AsyncTask<Integer,Void,Void>{

        int t,x,anInt;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getApplicationContext(),"started",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            x= Dataclass.fillcomp();
            t= Dataclass.iswon();
            anInt = params[0];
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(Dataclass.getRem(anInt))
            {
                highlightin(anInt);
                Dataclass.setTobeclickedno(anInt);
            }
            else
            {
                highlightin(-1);
                Dataclass.setTobeclickedno(-1);
            }
            if(x==1)
                Toast.makeText(getApplicationContext(),"Box "+Dataclass.getClickedno()+" conquered by X",Toast.LENGTH_LONG).show();
            else if(x==2)
                Toast.makeText(getApplicationContext(),"Box "+Dataclass.getClickedno()+" conquered by O",Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(),"Over",Toast.LENGTH_LONG).show();
            if(t==1)
            {
                Toast.makeText(getApplicationContext(),"Game won by X",Toast.LENGTH_LONG).show();
            }
            else if(t==2)
            {
                Toast.makeText(getApplicationContext(),"Game won by Y",Toast.LENGTH_LONG).show();
            }
        }
    }
    private class Validthread implements Runnable{
        @Override
        public void run() {
            Dataclass.fillcomp();
            //Toast.makeText(getApplicationContext(),"Over",Toast.LENGTH_LONG).show();
            int t= Dataclass.iswon();
            if(t==1)
            {
                //Toast.makeText(getApplicationContext(),"Game won by X",Toast.LENGTH_LONG).show();
            }
            else if(t==2)
            {
                //Toast.makeText(getApplicationContext(),"Game won by Y",Toast.LENGTH_LONG).show();
            }
        }

    }
}
