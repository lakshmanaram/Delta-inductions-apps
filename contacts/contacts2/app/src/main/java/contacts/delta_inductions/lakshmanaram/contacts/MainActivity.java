package contacts.delta_inductions.lakshmanaram.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> des = new ArrayList<>(),desimgs = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    String temp= null;
    Button add;
    ListView list;
    Button b2;
    Switch s1;
    EditText et;
    DataBaseAdapter dataBaseAdapter;
    LruCache<String, Bitmap> mMemoryCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
        setContentView(R.layout.activity_main);
        dataBaseAdapter = new DataBaseAdapter(getApplicationContext());
        dataBaseAdapter.GetAllData();
        names = dataBaseAdapter.getnames();
        images = dataBaseAdapter.getimages();
        list=(ListView) findViewById(R.id.listView);
        CustomAdapter adapt = new CustomAdapter(this,names,images,mMemoryCache);
        list.setAdapter(adapt);
        mMemoryCache = adapt.mMemoryCache;
        dataBaseAdapter.GetASCData();
        names = dataBaseAdapter.getnames();
        images = dataBaseAdapter.getimages();
        dataBaseAdapter.GetDESCData();
        desimgs= dataBaseAdapter.getimages();
        des= dataBaseAdapter.getnames();
        s1=(Switch) findViewById(R.id.switch1);
        s1.setChecked(true);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,add.class);
                startActivity(intent);
                finish();
            }
        });
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    CustomAdapter adapt = new CustomAdapter(getApplicationContext(),names,images,mMemoryCache);
                    list.setAdapter(adapt);
                    mMemoryCache = adapt.mMemoryCache;
                }
                else
                {
                    CustomAdapter adapt = new CustomAdapter(getApplicationContext(),des,desimgs,mMemoryCache);
                    list.setAdapter(adapt);
                    mMemoryCache = adapt.mMemoryCache;

                }
            }
        });
        b2=(Button) findViewById(R.id.SearchButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                et=(EditText) findViewById(R.id.SearchText);
                    temp=et.getText().toString();
                    if(temp!=null) {
                        int no = dataBaseAdapter.SearchDate(temp);
                        if (no <= 0) {
                            Toast t = Toast.makeText(getApplicationContext(), "Missing", Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        } else {
                            Toast t = Toast.makeText(getApplicationContext(), "CONTACT "+temp+" : FOUND", Toast.LENGTH_SHORT);
                            t.setGravity(Gravity.CENTER, 0, 0);
                            t.show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
class CustomAdapter extends ArrayAdapter<String>
{
    LruCache<String, Bitmap> mMemoryCache;
    Context cont;
    String delttext = null;
    ArrayList<String> images;
    ArrayList<String> nam;
    DataBaseAdapter dbadapter;
    CustomAdapter(Context c,ArrayList<String> names,ArrayList<String> imag,LruCache<String, Bitmap> mMemoryCache)
    {
        super(c, R.layout.row, R.id.text, names);
        this.dbadapter = new DataBaseAdapter(c);
        this.cont=c;
        this.nam=names;
        this.images=imag;
        this.mMemoryCache = mMemoryCache;
    }

    class ViewHolder
    {
        ImageView img;
        TextView name;
        Button del;
        Button ed;
        ViewHolder(View v)
        {
            img = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.text);
            del = (Button) v.findViewById(R.id.delete);
            ed = (Button) v.findViewById(R.id.edit);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder(v);
            v.setTag(holder);
        }
        else
        {
            holder =(ViewHolder) v.getTag();
        }
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delttext = nam.get(position);
                    dbadapter.del_data(delttext);
                    Intent i = new Intent(cont,MainActivity.class);
                    cont.startActivity(i);
                    ((Activity)cont).finish();
                }
            });
        holder.ed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cont,add.class);
                i.putExtra("name",nam.get(position));
                i.putExtra("pic",images.get(position));
                cont.startActivity(i);
                ((Activity)cont).finish();
            }
        });
        if(images.get(position)!=null) {
            final Bitmap bitmap = getBitmapFromMemCache(nam.get(position));
            if (bitmap != null) {
                holder.img.setImageBitmap(bitmap);
            } else {
                Bitmap bp = BitmapFactory.decodeFile(images.get(position));
                addBitmapToMemoryCache(nam.get(position), bp);
                holder.img.setImageBitmap(bp);
            }
        }
            holder.name.setText(nam.get(position));

        return v;
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


}
