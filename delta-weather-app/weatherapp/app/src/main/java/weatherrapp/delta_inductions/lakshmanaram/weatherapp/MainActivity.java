package weatherrapp.delta_inductions.lakshmanaram.weatherapp;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity  {

    String latitude="13.09",longitude="80.28";
    static ArrayList<HashMap<String,String>> results;
    static  GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = (GridView) findViewById(R.id.gridView);
        LocationManager manager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        Location lt=null;
        try{
            lt = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            latitude= Double.toString(lt.getLatitude());
            longitude = Double.toString(lt.getLongitude());
            MyTask task=new MyTask();
            task.execute();
        }catch(Exception e)
        {
            Log.d("error",e+"");
        }
        if(lt==null) {
            Toast.makeText(getApplicationContext(), "Network provider didn't provide any data. Showing results for chennai and around", Toast.LENGTH_LONG).show();
        }
    }

    public class MyTask extends AsyncTask<Void, Void,Void>{
        String url ="http://api.openweathermap.org/data/2.5/find?lat="+latitude+"&lon="+longitude+"&cnt=20&mode=xml";
//       String url ="http://api.openweathermap.org/data/2.5/find?lat=13.09&lon=80.28&cnt=15&mode=xml";       //in and around the place, temperature, weather description are nearly same
//String url ="http://api.openweathermap.org/data/2.5/find?lat=19.09&lon=70.28&cnt=15&mode=xml";    //for seeing the difference in the shown grid
        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream=null;
            try{
                URL open = new URL(url);
                HttpURLConnection connection = (HttpURLConnection)open.openConnection();
                connection.setRequestMethod("GET");
                 inputStream =connection.getInputStream();
                processXML(inputStream);
            }catch (Exception e)
            {
                e.printStackTrace();
                //Log.d("hello",e+"");
            }
            if(inputStream==null)
            {
                //Log.e("error","error");
            }
            //Log.e("error","noerror");

            return null;
        }
        public void processXML(InputStream inputStream) throws Exception{
            results = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document xmldoc = documentBuilder.parse(inputStream);
            Element root = xmldoc.getDocumentElement();
            HashMap<String,String> temperory;
            //Log.d("element",root.getTagName()+" ");
            NodeList itemslist = root.getElementsByTagName("item");
            NodeList itemchildren,itemschildren2;
            Node current;
            Node currentchild,currentchildren;
            NamedNodeMap city,temperature,weather;
            Node currentattribute;
            for(int i=0;i<itemslist.getLength();i++)
            {
                temperory = new HashMap<>();
                current =itemslist.item(i);
                itemchildren = current.getChildNodes();
                for(int j=0;j<itemchildren.getLength();j++)
                {
                    currentchild = itemchildren.item(j);
                    if(currentchild.getNodeName().equalsIgnoreCase("city"))
                    {
                        city=currentchild.getAttributes();
                        for(int k=0;k<city.getLength();k++)
                        {
                            currentattribute = city.item(k);
                            if(currentattribute.getNodeName().equalsIgnoreCase("name"))
                            {
                                temperory.put("city",currentattribute.getTextContent());
//                                Log.d("city",currentattribute.getTextContent()+"");
                            }
                        }
                        itemschildren2= currentchild.getChildNodes();
                        for(int l=0;l<itemschildren2.getLength();l++)
                        {
                            currentchildren=itemschildren2.item(l);
                            if(currentchildren.getNodeName().equalsIgnoreCase("coord"))
                            {
                                city=currentchildren.getAttributes();
                                for(int k=0;k<city.getLength();k++)
                                {
                                    currentattribute = city.item(k);
                                    if(currentattribute.getNodeName().equalsIgnoreCase("lon"))
                                    {
                                        temperory.put("lon", currentattribute.getTextContent() + "");
//                                        Log.d("lon", currentattribute.getTextContent() + "");
                                    }
                                    else if(currentattribute.getNodeName().equalsIgnoreCase("lat"))
                                    {
//                                        Log.d("lat", currentattribute.getTextContent() + "");
                                        temperory.put("lat", currentattribute.getTextContent() + "");
                                    }
                                }
                            }
                        }
                    }
                    else if(currentchild.getNodeName().equalsIgnoreCase("temperature"))
                    {
                        temperature=currentchild.getAttributes();
                        for(int k=0;k<temperature.getLength();k++)
                        {
                            currentattribute = temperature.item(k);
                            if(currentattribute.getNodeName().equalsIgnoreCase("value"))
                            {
                                String temper = currentattribute.getTextContent();
                                float celcius = Float.parseFloat(temper);
                                celcius -=273;
                                temper = Float.toString(celcius);
                                temperory.put("temperature", temper.substring(0,temper.length()-3)+ " C");
//                                Log.d("temperature", currentattribute.getTextContent() + "");
                            }
                        }
                    }
                    else if(currentchild.getNodeName().equalsIgnoreCase("weather"))
                    {
                        weather=currentchild.getAttributes();
                        for(int k=0;k<weather.getLength();k++)
                        {
                            currentattribute = weather.item(k);
                            if(currentattribute.getNodeName().equalsIgnoreCase("value"))
                            {
                                temperory.put("weather", currentattribute.getTextContent() + "");
//                                Log.d("weather", currentattribute.getTextContent() + "");
                            }
                        }
                    }
                }
                results.add(temperory);
            }
        }
        @Override
        protected void onPostExecute(Void aVoid) {
//            Log.d("results",results+"");
            gv.setAdapter(new adap(MainActivity.this,results));
        }
    }
    }



