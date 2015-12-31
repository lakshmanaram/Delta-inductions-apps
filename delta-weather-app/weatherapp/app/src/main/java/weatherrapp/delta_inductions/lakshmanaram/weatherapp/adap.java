package weatherrapp.delta_inductions.lakshmanaram.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class weather{
    String cityname;
    String temperature;
    String weather_description;
    weather(String cityname, String temperature, String weather_description)
    {
        this.cityname=cityname;
        if(cityname.length()>13)
        {
            this.cityname=cityname.substring(0,13);
        }
        this.temperature=temperature;
        this.weather_description=weather_description;
    }
}

public class adap extends BaseAdapter {
    int[] images ={R.drawable.scattered_clouds,R.drawable.sunny,R.drawable.sunny1,R.drawable.moderate_clouds,R.drawable.windy,R.drawable.rainy,R.drawable.clear_sky,R.drawable.low_clouds};;
    Context context;
    ArrayList<weather> w;
    adap(Context context,ArrayList<HashMap<String,String>> results)
    {
        this.context=context;
        w= new ArrayList<>();
        int i;
        //Log.d("results",results+"");
        for(i=0;i<results.size();i++)
        {

            HashMap<String,String > temperory = results.get(i);
            weather temp = new weather(temperory.get("city"),temperory.get("temperature"),temperory.get("weather"));
            w.add(temp);
        }
    }


    @Override
    public int getCount() {
        return w.size();
    }

    @Override
    public Object getItem(int position) {
        return w.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class  ViewHolder
    {
        RelativeLayout rl;
        TextView temperaturetv;
        TextView citytv;
        TextView descriptiontv;
        ViewHolder(View v)
        {
            temperaturetv=(TextView)v.findViewById(R.id.temperature);
            citytv=(TextView)v.findViewById(R.id.city);
            descriptiontv=(TextView)v.findViewById(R.id.weather);
            rl=(RelativeLayout)v.findViewById(R.id.rv);
        }

    }
    ViewHolder vh=null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View block =convertView;

        if(block == null)
        {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            block =inflater.inflate(R.layout.roe,parent,false);
            vh=new ViewHolder(block);
            block.setTag(vh);
            //Log.d("hello","again set");
        }
        else
        {
            vh=(ViewHolder)block.getTag();

        }
        weather temp = w.get(position);
        vh.citytv.setText(temp.cityname);
        vh.descriptiontv.setText(temp.weather_description);
        vh.temperaturetv.setText(temp.temperature);
        vh.rl.setBackgroundResource(images[position%8]);
        return block;
    }
}
