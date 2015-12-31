package com.android.lakshmanaram.studentaide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lakshmanaram on 10/7/15.
 */
public class SemDbAdapter {
    Context context;
    SemDbHelper semDbHelper;
    ArrayList<String> subnames,subcodes;
    ArrayList<Integer> percentmin,mons,tues,weds,thus,fris,sats,suns,cas,tcs;
    public SemDbAdapter(Context context)
    {
        this.context = context;
        semDbHelper = new SemDbHelper(context);
    }
    public void GetAllData()
    {
            SQLiteDatabase sqLiteDatabase = semDbHelper.getWritableDatabase();
            String[] colmns = {semDbHelper.SUBJECT_NAME, semDbHelper.SUBJECT_CODE, semDbHelper.MIN_PERCENT, semDbHelper.MON, semDbHelper.TUE, semDbHelper.WED, semDbHelper.THU, semDbHelper.FRI, SemDbHelper.SAT, semDbHelper.SUN, semDbHelper.TOTAL_CLASSES, semDbHelper.CLASSES_ATTENDED};
            Cursor cursor = sqLiteDatabase.query(semDbHelper.TABLE_NAME, colmns, null, null, null, null, null);

            subnames = new ArrayList<>();
        subcodes = new ArrayList<>();
        percentmin = new ArrayList<>();
        mons = new ArrayList<>();
        tues = new ArrayList<>();
        weds = new ArrayList<>();
        thus = new ArrayList<>();
        fris = new ArrayList<>();
        sats = new ArrayList<>();
        suns = new ArrayList<>();
        cas = new ArrayList<>();
        tcs = new ArrayList<>();
        while(cursor.moveToNext())
        {
            int i1 = cursor.getColumnIndex(semDbHelper.SUBJECT_NAME);
            int i2 = cursor.getColumnIndex(semDbHelper.SUBJECT_CODE);
            int i3 = cursor.getColumnIndex(semDbHelper.MIN_PERCENT);
            int i4 = cursor.getColumnIndex(semDbHelper.MON);
            int i5 = cursor.getColumnIndex(semDbHelper.TUE);
            int i6 = cursor.getColumnIndex(semDbHelper.WED);
            int i7 = cursor.getColumnIndex(semDbHelper.THU);
            int i8 = cursor.getColumnIndex(semDbHelper.FRI);
            int i9 = cursor.getColumnIndex(semDbHelper.SAT);
            int i10 = cursor.getColumnIndex(semDbHelper.SUN);
            int i11 = cursor.getColumnIndex(semDbHelper.CLASSES_ATTENDED);
            int i12 = cursor.getColumnIndex(semDbHelper.TOTAL_CLASSES);
            subnames.add(cursor.getString(i1));
            subcodes.add(cursor.getString(i2));
            percentmin.add(cursor.getInt(i3));
            mons.add(cursor.getInt(i4));
            tues.add(cursor.getInt(i5));
            weds.add(cursor.getInt(i6));
            thus.add(cursor.getInt(i7));
            fris.add(cursor.getInt(i8));
            sats.add(cursor.getInt(i9));
            suns.add(cursor.getInt(i10));
            cas.add(cursor.getInt(i11));
            tcs.add(cursor.getInt(i12));

        }
        cursor.close();
    }

    public ArrayList<Integer> getCas() {
        return cas;
    }

    public ArrayList<Integer> getFris() {
        return fris;
    }

    public ArrayList<Integer> getMons() {
        return mons;
    }

    public ArrayList<Integer> getPercentmin() {
        return percentmin;
    }

    public ArrayList<Integer> getSats() {
        return sats;
    }

    public ArrayList<Integer> getSuns() {
        return suns;
    }

    public ArrayList<Integer> getTcs() {
        return tcs;
    }

    public ArrayList<Integer> getThus() {
        return thus;
    }

    public ArrayList<Integer> getTues() {
        return tues;
    }

    public ArrayList<Integer> getWeds() {
        return weds;
    }

    public ArrayList<String> getSubcodes() {
        return subcodes;
    }

    public ArrayList<String> getSubnames() {
        return subnames;
    }

    public long add_data(String name,String code, int min, int mon, int tue, int wed, int thu, int fri, int sat, int sun)
    {
        SQLiteDatabase sqLiteDatabase = semDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(semDbHelper.SUBJECT_NAME,name);
        cv.put(semDbHelper.SUBJECT_CODE,code);
        cv.put(semDbHelper.MIN_PERCENT,min);
        cv.put(semDbHelper.MON,mon);
        cv.put(semDbHelper.TUE,tue);
        cv.put(semDbHelper.WED,wed);
        cv.put(semDbHelper.THU,thu);
        cv.put(semDbHelper.FRI,fri);
        cv.put(semDbHelper.SAT,sat);
        cv.put(semDbHelper.SUN,sun);
        cv.put(semDbHelper.CLASSES_ATTENDED,0);
        cv.put(semDbHelper.TOTAL_CLASSES,0);
        return sqLiteDatabase.insert(semDbHelper.TABLE_NAME,semDbHelper.SUN,cv);

    }

    static class SemDbHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "semester";
        private static String TABLE_NAME = "sem";
        Context context = null;
        private static final int DATABASE_VERSION = 1;
        private static final String ID = "_id";
        private static final String SUBJECT_NAME = "subject";
        private static final String SUBJECT_CODE = "code";
        private static final String MIN_PERCENT = "minimum_percentage";
        private static final String MON = "mon";
        private static final String TUE = "tue";
        private static final String WED = "wed";
        private static final String THU = "thu";
        private static final String FRI = "fri";
        private static final String SAT = "sat";
        private static final String SUN = "sun";
        private static final String TOTAL_CLASSES = "total_classes";
        private static final String CLASSES_ATTENDED = "classes_attended";
        private static final String drop = "DROP TABLE IF EXISTS "+TABLE_NAME;
        private static final String create = "CREATE TABLE "+TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SUBJECT_CODE+" VARCHAR(10), "+SUBJECT_NAME+" VARCHAR(30), "+MIN_PERCENT+" INTEGER, "+MON+" INTEGER, "+TUE+" INTEGER, "+WED+" INTEGER, "+THU+" INTEGER, "+FRI+" INTEGER, "+SAT+" INTEGER, "+SUN+" INTEGER, "+TOTAL_CLASSES+" INTEGER, "+CLASSES_ATTENDED+" INTEGER);";
        public SemDbHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
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
