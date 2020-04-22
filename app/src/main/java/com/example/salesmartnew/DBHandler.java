package com.example.salesmartnew;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHandler extends SQLiteOpenHelper {

    Context context;
    private static String DATABASE_NAME = "SaleSmart.db";
    private static int DATABASE_VERSION = 1;

    private static String createTableQuery = "create table customer (image BLOB" + ",cFullName TEXT" + ",cUserName TEXT" + ",cPassword TEXT" + ",cConfirmPassword TEXT)";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInByte;

    public DBHandler( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(createTableQuery);
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCustomer(RegisterModel registerModel){

        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Bitmap bitmapImage =registerModel.getImage();

            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
            imageInByte = byteArrayOutputStream.toByteArray();
            ContentValues contentValues = new ContentValues();

            contentValues.put("image",imageInByte);
            contentValues.put("cFullName",registerModel.getFullName());
            contentValues.put("cUserName",registerModel.getUserName());
            contentValues.put("cPassword",registerModel.getPassword());
            contentValues.put("cConfirmPassword",registerModel.getConfirmPassword());

            long checkQuery = sqLiteDatabase.insert("SaleSmart", null,contentValues);
            if(checkQuery != -1){
                Toast.makeText(context,"Data Added",Toast.LENGTH_SHORT).show();
                sqLiteDatabase.close();
            }
            else {
                Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
    }

}
