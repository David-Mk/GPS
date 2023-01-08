

package com.example.gps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Locations", null, 1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Locations(time TEXT primary key , latitude TEXT , longtitude TEXT)");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists Locations");
    }

    public Boolean insertlocation(String time,String latitude, String longtitude) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",time);
        contentValues.put("latitude", latitude);
        contentValues.put("longtitude", longtitude);

        long result = sqLiteDatabase.insert("Locations", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatelocation(String time,String latitude, String longtitude) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time",time);
        contentValues.put("latitude", latitude);
        contentValues.put("longtitude", longtitude);
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Locations where latitude = ?",new String [] {latitude});
        if (cursor.getCount() > 0){
            long result = sqLiteDatabase.update("Locations", contentValues,"latitude=?",new String[]{latitude});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }

    public Cursor getdata (){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from Locations",null);
        return cursor;
    }
}