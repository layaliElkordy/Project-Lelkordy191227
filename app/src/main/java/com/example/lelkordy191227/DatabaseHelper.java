package com.example.lelkordy191227;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "USERS_DATA";
    public static final String c1 = "userId";
    public static final String c2 = "emailAddress";
    public static final String c3 = "firstName";
    public static final String c4 = "lastName";
    public static final String c5 = "phoneNumber";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME +" ( "
                + c1 +" INTEGER PRIMARY KEY , "+
                c2 +" TEXT NOT NULL, " +
                c3 +" TEXT NOT NULL, " +
                c4 +" TEXT NOT NULL, " +
                c5 +" TEXT NOT NULL) ");

    }
    /******************************************ADD USER****************************************************/
    public void AddUser(String userId, String emailAddress, String firstName, String lastName, String phoneNumber ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c1,userId);
        values.put(c2,emailAddress);
        values.put(c3, firstName);
        values.put(c4, lastName);
        values.put(c5,phoneNumber);
        db.insert(TABLE_NAME,null, values);
    }
    /******************************************VIEW USERS****************************************************/
    public Cursor ViewUsers(){
        SQLiteDatabase db  =  this.getWritableDatabase();
        Cursor x = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return x;
    }
    /******************************************DELETE USER****************************************************/
    public Integer DeleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "userId = ?", new String[]{id});
    }
    /******************************************VIEW SPECIFIC USER****************************************************/
    public Cursor ViewSpecificUser(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE userId = ?", new String[]{id});
        if (data != null)
            data.moveToFirst();
        return data;
    }
    /******************************************UPDATE USER****************************************************/
    public void UpdateUser(String userId, String emailAddress, String firstName, String lastName, String phoneNumber ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(c2,emailAddress);
        values.put(c3, firstName);
        values.put(c4, lastName);
        values.put(c5,phoneNumber);
        db.update(TABLE_NAME,values,"userId = ?", new String[]{userId});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
