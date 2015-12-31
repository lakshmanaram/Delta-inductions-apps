package contacts.delta_inductions.lakshmanaram.contacts;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class add extends ActionBarActivity {

    private static String TEMP_PHOTO_FILE =".jpg";
    String name;
    EditText editText;
    Button addbutton,img;
    ImageView imageView;
    String picturePath = null;
    DataBaseAdapter dbadapter;
    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap bitmap;
    String old = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        img=(Button) findViewById(R.id.image);
        addbutton = (Button) findViewById(R.id.addbutton);
        editText = (EditText) findViewById(R.id.name);
        imageView = (ImageView) findViewById(R.id.imageview);
        if(b!=null)
        {
            if((String)b.get("pic")!=null)
                imageView.setImageBitmap(BitmapFactory.decodeFile((String)b.get("pic")));
            old = (String) b.get("name");
            editText.setHint(old);
            addbutton.setText("CLICK TO EDIT");
        }
        else
        {
            editText.setHint("");
            addbutton.setText("ADD");
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.setType("image/*");
                i.putExtra("crop", "true");
                i.putExtra("aspectX", 1);
                i.putExtra("aspectY", 1);
                i.putExtra("outputX", 300);
                i.putExtra("outputY", 300);
                i.putExtra("return-data", true);
                i.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
                i.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText.getText().toString();
                if(name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter a Valid Name",Toast.LENGTH_SHORT).show();

                }
                else {
                    dbadapter = new DataBaseAdapter(getApplicationContext());
                    if (old == null) {
                        dbadapter.add_data(name, picturePath);
                    } else {
                        dbadapter.update_data(old, name, picturePath);
                    }
                    Intent intent = new Intent(add.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    //Toast.makeText(getApplicationContext(),name+" received",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Toast.makeText(getApplicationContext(),"add activity",Toast.LENGTH_LONG).show();
    }
    private Uri getTempUri() {
        return Uri.fromFile(getTempFile());
    }
    private File getTempFile() {

        Calendar c= Calendar.getInstance();
        c.setTime(new Date());
        int d=c.get(Calendar.DATE);
        int m=c.get(Calendar.MONTH);
        int y=c.get(Calendar.YEAR);
        TEMP_PHOTO_FILE=Long.toString(System.currentTimeMillis())+"_"+
                Integer.toString(d)+"_"+Integer.toString(m)+"_"+Integer.toString(y)+".jpg";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {


            File file = new File(Environment.getExternalStorageDirectory(),TEMP_PHOTO_FILE);

            try {
                file.createNewFile();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"file creation error",Toast.LENGTH_LONG).show();
            }


            return file;
        } else {
            File file = new File(Environment.getRootDirectory(),TEMP_PHOTO_FILE);
            Toast.makeText(getApplicationContext(),"Dunno iif it'll work",Toast.LENGTH_LONG).show();
            return file;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            /*Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            */
            picturePath= Environment.getExternalStorageDirectory()
                    +"/"+TEMP_PHOTO_FILE;

            bitmap = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(bitmap);

        }
    }
}
