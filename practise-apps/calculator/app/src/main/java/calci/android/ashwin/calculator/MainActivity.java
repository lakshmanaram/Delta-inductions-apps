package calci.android.ashwin.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bplus,bminus,bmult,bdiv,beq,breset;
    List<Integer> num = new ArrayList<>();
    int i;
    char op;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            t = (TextView)findViewById(R.id.textView);
            b0=(Button) findViewById(R.id.bb0);
            b1=(Button) findViewById(R.id.bb1);

            b2=(Button) findViewById(R.id.bb2);
            b3=(Button) findViewById(R.id.bb3);
            b4=(Button) findViewById(R.id.bb4);
            b5=(Button) findViewById(R.id.bb5);
            b6=(Button) findViewById(R.id.bb6);
            b7=(Button) findViewById(R.id.bb7);
            b8=(Button) findViewById(R.id.bb8);
            b9=(Button) findViewById(R.id.bb9);
            bplus=(Button) findViewById(R.id.bbplus);
            bminus=(Button) findViewById(R.id.bbminus);
            bmult=(Button) findViewById(R.id.bbmult);
            bdiv=(Button) findViewById(R.id.bbdiv);
            beq=(Button) findViewById(R.id.bbeq);
            breset=(Button) findViewById(R.id.bbreset);
            num.add(0);
            num.add(0);
            i=0;
            b0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10);
                    t.setText(num.get(i)+"");

                }
            });
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     num.set(i,num.get(i)*10+1);
                     t.setText(num.get(i)+"");
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+2);
                    t.setText(num.get(i)+"");
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+3);
                    t.setText(num.get(i)+"");
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+4);
                    t.setText(num.get(i)+"");
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+5);
                    t.setText(num.get(i)+"");
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+6);
                    t.setText(num.get(i)+"");
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+7);
                    t.setText(num.get(i)+"");
                }
            });
            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+8);
                    t.setText(num.get(i)+"");
                }
            });
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(i,num.get(i)*10+9);
                    t.setText(num.get(i)+"");
                }
            });
            bplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        op='+';
                        i=1;
                    }
                    else
                    {
                        num.set(0,operator(op,num.get(0),num.get(1)));
                        num.set(1,0);
                        op='+';

                    }
                    t.setText(num.get(0)+"");
                }
            });
            bminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        op='-';
                        i=1;
                    }
                    else
                    {
                        num.set(0,operator(op,num.get(0),num.get(1)));
                        num.set(1,0);
                        op='-';
                    }
                    t.setText(num.get(0)+"");
                }

            });
            bmult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        op='*';
                        i=1;
                    }
                    else
                    {
                        num.set(0,operator(op,num.get(0),num.get(1)));
                        num.set(1,0);
                        op='*';
                    }
                    t.setText(num.get(0)+"");
                }
            });
            bdiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(i==0)
                    {
                        op='/';
                        i=1;
                    }
                    else
                    {
                        num.set(0,operator(op,num.get(0),num.get(1)));
                        num.set(1,0);
                        op='/';
                    }
                    t.setText(num.get(0)+"");
                }
            });
            breset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i=0;
                    num.set(0,0);
                    num.set(1,0);
                    t.setText("0");
                }
            });
            beq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.set(0,operator(op,num.get(0),num.get(1)));
                    t.setText(num.get(0)+"");
                    i=0;
                    num.set(1,0);
                }
            });

    }
    public int operator(char c,int a, int b)
    {
        if(c=='+')
            return a+b;
        else if(c=='-')
            return a-b;
        else if(c=='*')
            return a*b;
        else
        {
            if(b!=0)
                return a/b;

            else
            {
                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                return 0;
            }
        }
    }


}
