package tictactoe.ultimate.com.android.lakshmanaram.ultimate_tictactoe;

import android.app.ActionBar;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class singlegrid_frag extends Fragment {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9;
    int id;
    communicator comm;
    LinearLayout big;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.d("hel", this.getId() + "");
        id = this.getId();
        Dataclass.addviewid(this.getId());
        comm = (communicator) getActivity();
    }

    public void chhigh(int to)
    {
        if(to==Dataclass.giveClickedno(id))
        {
            big.setBackgroundColor(getResources().getColor(R.color.high));
        }
        else
        {
            big.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_singlegrid_frag,container,false);
        final View v1 = v;
        big = (LinearLayout)v.findViewById(R.id.big);
        b1 = (Button) v.findViewById(R.id.but1);
        b2 = (Button) v.findViewById(R.id.but2);
        b3 = (Button) v.findViewById(R.id.but3);
        b4 = (Button) v.findViewById(R.id.but4);
        b5 = (Button) v.findViewById(R.id.but5);
        b6 = (Button) v.findViewById(R.id.but6);
        b7 = (Button) v.findViewById(R.id.but7);
        b8 = (Button) v.findViewById(R.id.but8);
        b9 = (Button) v.findViewById(R.id.but9);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(0))
                    {
                        b1.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(0);
                        comm.validate(0);
                        if(Dataclass.getRem(0))
                        {
                            comm.highlightin(0);
                            Dataclass.setTobeclickedno(0);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }

                    }

                    else
                        Log.d("hel","buh");

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(1))
                    {
                        b2.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(1);
                        comm.validate(1);
                        if(Dataclass.getRem(1))
                        {
                            comm.highlightin(1);
                            Dataclass.setTobeclickedno(1);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }

                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(2))
                    {
                        b3.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(2);
                        comm.validate(2);
                        if(Dataclass.getRem(2))
                        {
                            comm.highlightin(2);
                            Dataclass.setTobeclickedno(2);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }

                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(3))
                    {
                        b4.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(3);
                        comm.validate(3);
                        if(Dataclass.getRem(3))
                        {
                            comm.highlightin(3);
                            Dataclass.setTobeclickedno(3);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }

                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(4))
                    {
                        b5.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(4);
                        comm.validate(4);
                        if(Dataclass.getRem(4))
                        {
                            comm.highlightin(4);
                            Dataclass.setTobeclickedno(4);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }
                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(5))
                    {
                        b6.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(5);
                        comm.validate(5);
                        if(Dataclass.getRem(5))
                        {
                            comm.highlightin(5);
                            Dataclass.setTobeclickedno(5);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }
                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(6))
                    {
                        b7.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(6);
                        comm.validate(6);
                        if(Dataclass.getRem(6))
                        {
                            comm.highlightin(6);
                            Dataclass.setTobeclickedno(6);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }
                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(7))
                    {
                        b8.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(7);
                        comm.validate(7);
                        if(Dataclass.getRem(7))
                        {
                            comm.highlightin(7);
                            Dataclass.setTobeclickedno(7);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }
                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataclass.findclickedno(v1.getId());
                if((Dataclass.getTobeclickedno() == Dataclass.getClickedno())||(Dataclass.getTobeclickedno()==-1))
                {
                    if(Dataclass.isgridtilefilled(8))
                    {
                        b9.setText(Dataclass.getValue() + "");
                        Dataclass.setgridtilefilled(8);
                        comm.validate(8);
                        if(Dataclass.getRem(8))
                        {
                            comm.highlightin(8);
                            Dataclass.setTobeclickedno(8);
                        }
                        else
                        {
                            comm.highlightin(-1);
                            Dataclass.setTobeclickedno(-1);
                        }
                    }
                    else
                        Log.d("hel","buh");
                    //Log.d("hel","double check works   "+Dataclass.getClickedno()+":::::::"+v1.getId());

                }
                else
                {
                    Log.d("hel","improper tile");
                }
            }
        });

        return v;
    }
}
