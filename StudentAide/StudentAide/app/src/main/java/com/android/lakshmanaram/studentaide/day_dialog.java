package com.android.lakshmanaram.studentaide;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.Date;

/**
 * Created by lakshmanaram on 21/7/15.
 */
public class day_dialog extends DialogFragment{
    Bundle args;
    String datestr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args= getArguments();
        datestr= args.getString("date");
        try {
            Datacls.setClose(false);
        } catch (Exception e) {
            Log.d("hel",e.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setCancelable(false);
        View v = inflater.inflate(R.layout.dialog_day,null);
        RadioButton presentcb = (RadioButton) v.findViewById(R.id.presentcb) , absentcb = (RadioButton) v.findViewById(R.id.absentcb);
        Button close = (Button)v.findViewById(R.id.close);
        presentcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Datacls.setvalues(1, 0);
                Datacls.setClose(true);
                Log.d("hel","present");
                dismiss();
            }
        });
        absentcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Datacls.setvalues(0,1);
                Datacls.setClose(true);
                Log.d("hel","absent");
                dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("hel","closed");
                dismiss();
            }
        });
        return v;
    }
}
