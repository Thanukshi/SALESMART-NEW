package com.example.salesmartnew.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler( Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database  = getWritableDatabase();
        database.execSQL(sql);
    }

    //insert data to the database
    public void insertData(byte[] image, String fullName, String userName, String password, String confirmPass){
        SQLiteDatabase database  = getWritableDatabase();
        String sql = "INSERT INTO SALESMART VALUES(NULL, ?, ?, ?, ?, ?)";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindBlob(0, image);
        sqLiteStatement.bindString(1,fullName);
        sqLiteStatement.bindString(2,userName);
        sqLiteStatement.bindString(3,password);
        sqLiteStatement.bindString(4,confirmPass);

        sqLiteStatement.executeInsert();
    }

    //update the data in database
    public void updateData(byte[] image, String fullName, String userName, String password, String confirmPass, String id) {
        SQLiteDatabase database  = getWritableDatabase();
        String sql = "UPDATE SALESMART SET image = ?, fullName = ?, userName = ?, password = ?, confirmPass = ?  WHERE id = ?";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);

        sqLiteStatement.bindBlob(0, image);
        sqLiteStatement.bindString(1,fullName);
        sqLiteStatement.bindString(2,userName);
        sqLiteStatement.bindString(3,password);
        sqLiteStatement.bindString(4,confirmPass);
        sqLiteStatement.bindString(5, id);

        sqLiteStatement.execute();
        database.close();
    }

    //delete data from database
    public void deleteData(String id){

        SQLiteDatabase database  = getWritableDatabase();
        String sql = "DELETE FROM SALESMART WHERE id = ?";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindString(1 , id);

        sqLiteStatement.execute();
        database.close();
    }

    //view the data from database
    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
