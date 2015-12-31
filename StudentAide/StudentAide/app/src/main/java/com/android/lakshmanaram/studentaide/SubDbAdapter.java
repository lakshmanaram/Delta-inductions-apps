package com.android.lakshmanaram.studentaide;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by lakshmanaram on 21/7/15.
 */
public class SubDbAdapter {
    Context context;
    SubDbHelper subDbHelper;
    ArrayList<Date> dates;
    ArrayList<Integer> present,Absent;
    public SubDbAdapter(Context context,String sub_name)
    {
        this.context = context;
        subDbHelper = new SubDbHelper(context,sub_name);
    }
    public long add_data(String date1,int present, int absent)
    {
        SQLiteDatabase sqLiteDatabase = subDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SubDbHelper.DATE, date1);
        cv.put(subDbHelper.PRESENT,present);
        cv.put(subDbHelper.ABSENT,absent);
        return sqLiteDatabase.insert(subDbHelper.TABLE_NAME,null,cv);

    }

    static class SubDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "semester";
        private static String TABLE_NAME = "";
        Context context = null;
        private static final int DATABASE_VERSION = 1;
        private static final String ID = "_id";
        private static final String DATE = "date";
        private static final String PRESENT = "present_classes";
        private static final String ABSENT = "absent_classes";
        private static final String drop = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private static final String create = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DATE+" DATE, "+PRESENT+" INTEGER, "+ABSENT+" INTEGE;";
        public SubDbHelper(Context context,String sub_name)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            TABLE_NAME = sub_name;
            this.context = context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Toast.makeText(context, "Database Created", Toast.LENGTH_LONG).show();
                db.execSQL(create);
            } catch (SQLException e) {
                Log.d("hel", e.toString());
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
