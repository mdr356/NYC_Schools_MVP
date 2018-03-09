package com.trinityempire.a20180306_mdr_nycschools.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trinityempire.a20180306_mdr_nycschools.logs.SchoolsLog;
import com.trinityempire.a20180306_mdr_nycschools.view_main.model.Schools;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "favorite";

    private static final String TABLE_NAME = "schools_table";

    private static final String KEY_ID = "id";
    private static final String KEY_SCHOOLS = "schools";
    private static final String KEY_SCHOOL_NAME = "school_name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        SchoolsLog.d("onCreate sqlite table");
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCHOOL_NAME +" TEXT," + KEY_SCHOOLS +" BLOB" +")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        SchoolsLog.d("onUpgrade");

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    /**
     * Read all data from the database.
     * An SQL BLOB is a built-in type that stores a Binary Large Object
     */
    public List<Schools> getAllData() throws SQLException {
        SchoolsLog.d("getAllData() from favorite database");

        List<Schools> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            do {
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(KEY_SCHOOLS));
                String json = new String(blob);
                Gson gson = new Gson();
                Schools obj = gson.fromJson(json, new TypeToken<Schools>(){}.getType());
                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }
    /**
     * save school data
     */
    public void insertData(Schools data) throws Exception{

        SchoolsLog.d("Inserting into database, using Gson to store object into Blob");
        SQLiteDatabase db = this.getWritableDatabase();

        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(KEY_SCHOOLS, gson.toJson(data).getBytes());
        values.put(KEY_SCHOOL_NAME, data.getSchool_name().replaceAll("'",""));
        db.insert(TABLE_NAME,null,values);

        db.close();

    }

    // deleting
    public boolean deleteData(String school_name) throws Exception{
        SchoolsLog.d("deleteData() is called in database");
        school_name = school_name.replace("'","'");

        SQLiteDatabase db = this.getWritableDatabase();
        boolean rtn = true;
        try {
            db.delete(TABLE_NAME, KEY_SCHOOL_NAME + "=?",new String[] {school_name});
        } catch (Exception e) {
            SchoolsLog.d("Error occured while removing data from database" + e);
            rtn =  false;
        } finally {
            db.close();
        }

        return rtn;
    }

    // select an item
    public boolean isThere(String school_name) throws Exception {
        school_name = school_name.replace("'","");
        SchoolsLog.d("isThere() is called, to check if school is there in database");
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "
                +KEY_SCHOOL_NAME+"='"+school_name+"'", null);
        cursor.moveToFirst();
        Boolean rtn;
        if (cursor != null && cursor.getCount()== 0) {
            rtn = true;
        } else {
            rtn = false;
        }
        db.close();
        cursor.close();
        return rtn;
    }



}
