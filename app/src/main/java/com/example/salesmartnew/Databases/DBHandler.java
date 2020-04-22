package com.example.salesmartnew.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "SaleSmart.db";
    private static int DATABASE_VERSION = 1;

    private static String createTableQuery = "create table customer (image BLOB" + ",cFullName TEXT" + ",cUserName TEXT" + ",cPassword TEXT" + "cConfirmPassword TEXT)";
    public DBHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

        }catch (Exception e){
            
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
