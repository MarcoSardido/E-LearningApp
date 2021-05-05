package com.example.e_learningapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DBNAME = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB)
    {
        MyDB.execSQL("create Table users(FullName TEXT primary key, Email TEXT, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion)
    {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String FullName, String Email, String Password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname", FullName);
        contentValues.put("email", Email+"@gmail.com");
        contentValues.put("password", Password);

        long results = MyDB.insert("users", null, contentValues);

        if(results == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Boolean checkUser(String Email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where Email = ?", new String[] {Email});

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean authorizeUser(String Email, String Password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where Email = ? and Password = ?", new String[] {Email, Password});

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getUserName(String fName)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor checkName = MyDB.rawQuery("select FullName from users where FullName = ?", new String[] {fName});

        if(checkName.equals(fName))
        {
            Cursor getName = MyDB.rawQuery("select FullName from users ", null);
            return getName;
        }
        return null;
    }
}