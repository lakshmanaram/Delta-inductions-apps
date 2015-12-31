package com.android.lakshmanaram.studentaide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lakshmanaram on 21/7/15.
 */
class subject{
    String name;
    String code;
    int min;
    //int tcs;
    //int cas;
    subject(String name, String code, int min)//, int cas, int tcs)
    {
        this.name = name;
        this.code = code;
        this.min = min;
        //this.cas = cas;
        //this.tcs = tcs;
    }
}
public class customadap extends BaseAdapter{
    ArrayList<subject> s;
    ArrayList<String> subject_names;
    ArrayList<String> subject_codes;
    ArrayList<Integer> min_percent;
    //ArrayList<Integer> tot_classes;
    //ArrayList<Integer> classes_attended;
    Context context;
    customadap(Context context, ArrayList<String> subs, ArrayList<String> subject_codes, ArrayList<Integer> min_percent)//,ArrayList<Integer> classes_attended,ArrayList<Integer> tot_classes)
    {
        this.context = context;
        this.subject_codes = subject_codes;
        //this.classes_attended = classes_attended;
        this.subject_names = subs;
        this.min_percent = min_percent;
        //this.tot_classes = tot_classes;
        s = new ArrayList<>();
        for(int i=0;i<subs.size();i++)
        {
            subject stemp = new subject(subject_names.get(i),subject_codes.get(i),min_percent.get(i));//,classes_attended.get(i),tot_classes.get(i));
            s.add(stemp);
        }
    }
    @Override
    public int getCount() {
        return s.size();
    }

    @Override
    public Object getItem(int position) {
        return s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder{
        RelativeLayout rl;
        TextView subtv;
        ViewHolder(View v)
        {
            rl = (RelativeLayout) v.findViewById(R.id.gridrl);
            subtv = (TextView) v.findViewById(R.id.subtv);
        }
    }

    ViewHolder vh = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cell = convertView;
        if(cell == null)
        {
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            cell = li.inflate(R.layout.subject_cell,parent,false);
            vh = new ViewHolder(cell);
            cell.setTag(vh);
        }
        else {
            vh = (ViewHolder) cell.getTag();
        }
        subject temp = s.get(position);
        vh.subtv.setText(temp.name);
        return cell;
    }
}
