package contacts.delta_inductions.lakshmanaram.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseAdapter {
    DataBaseHelper dbhelper;
    Context context;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> imgs = new ArrayList<>();
    public DataBaseAdapter(Context context)
    {
        this.context = context;
        dbhelper = new DataBaseHelper(context);
    }
    public void GetAllData()
    {
            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
            String[] columns = {DataBaseHelper.NAME,DataBaseHelper.PIC};
            Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_NAME,columns,null,null,null,null,null);
            names = new ArrayList<>();
            imgs = new ArrayList<>();
            while(cursor.moveToNext())
            {
                int index1 = cursor.getColumnIndex(DataBaseHelper.NAME);
                int index2 = cursor.getColumnIndex(DataBaseHelper.PIC);
                names.add(cursor.getString(index1));
                imgs.add(cursor.getString(index2));
            }
            cursor.close();
    }
    public void GetASCData()
    {
            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
            String[] columns = {DataBaseHelper.NAME,DataBaseHelper.PIC};
            Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_NAME,columns,null,null,null,null,DataBaseHelper.NAME+" ASC");
            names = new ArrayList<>();
            imgs = new ArrayList<>();
            while(cursor.moveToNext())
            {
                int index1 = cursor.getColumnIndex(DataBaseHelper.NAME);
                int index2 = cursor.getColumnIndex(DataBaseHelper.PIC);
                names.add(cursor.getString(index1));
                imgs.add(cursor.getString(index2));
            }
            cursor.close();
    }
    public void GetDESCData()
    {
            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
            String[] columns = {DataBaseHelper.NAME,DataBaseHelper.PIC};
            Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_NAME,columns,null,null,null,null,DataBaseHelper.NAME+" DESC");
            names = new ArrayList<>();
            imgs = new ArrayList<>();
            while(cursor.moveToNext())
            {
                int index1 = cursor.getColumnIndex(DataBaseHelper.NAME);
                int index2 = cursor.getColumnIndex(DataBaseHelper.PIC);
                names.add(cursor.getString(index1));
                imgs.add(cursor.getString(index2));
            }
            cursor.close();
    }
    public int SearchDate(String st)
    {
            SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
            String[] columns = {DataBaseHelper.NAME,DataBaseHelper.PIC};
            Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_NAME,columns,DataBaseHelper.NAME+" = '"+st+"'",null,null,null,DataBaseHelper.NAME+" ASC");
            int no = cursor.getCount();
            names = new ArrayList<>();
            imgs = new ArrayList<>();
            while(cursor.moveToNext())
            {
                int index1 = cursor.getColumnIndex(DataBaseHelper.NAME);
                int index2 = cursor.getColumnIndex(DataBaseHelper.PIC);
                names.add(cursor.getString(index1));
                imgs.add(cursor.getString(index2));
            }
            cursor.close();
        return no;
    }

    public ArrayList<String> getnames()
    {
        return names;
    }
    public ArrayList<String> getimages()
    {
        return imgs;
    }
    public int del_data(String name)
    {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        String[] whereargs={name};
        return sqLiteDatabase.delete(DataBaseHelper.TABLE_NAME,DataBaseHelper.NAME+" = ?",whereargs);
    }
    public int update_data(String old, String name,String picpath)
    {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.NAME,name);
        cv.put(DataBaseHelper.PIC,picpath);
        String[] whereargs={old};
        return sqLiteDatabase.update(DataBaseHelper.TABLE_NAME,cv,DataBaseHelper.NAME+" = ?",whereargs);

    }
    public long add_data(String name,String picpath)
    {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.NAME,name);
        cv.put(DataBaseHelper.PIC,picpath);
        return sqLiteDatabase.insert(DataBaseHelper.TABLE_NAME,DataBaseHelper.PIC,cv);

    }
    static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "contactsdb";
        private static final String TABLE_NAME = "contacts";
        private static final int DATABASE_VERSION = 2;
        private static final String ID = "_id";
        private static final String NAME = "Name";
        Context context=null;
        private static final String drop = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private static final String PIC = "Pic";
        private static final String create = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(100),"+PIC+" TEXT);";
        public DataBaseHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                Toast.makeText(context,"Database Created",Toast.LENGTH_LONG).show();
                db.execSQL(create);
            } catch (SQLException e) {
                Log.d("hel",e.toString());
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                db.execSQL(drop);
                onCreate(db);
            } catch (SQLException e) {
                Log.d("hel",e.toString());
            }
        }
    }
}
