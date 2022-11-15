package com.example.thirukural;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteDataBase extends SQLiteOpenHelper {

    public static final String db_name = "kural_app";
    public static final String th_name = "favourite";

    public static final int version = 1;

    Context context;

    SQLiteDatabase sqLiteDatabase;



    public FavouriteDataBase(Context context) {
        super(context, db_name, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+th_name+" (kural_number text, primary key(kural_number));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void removeFavourite(String kural_number){

        sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL(String.format("delete from %s where kural_number =\'%s\'",th_name,kural_number));

    }

    public void addFavourite(String kural_number){

        sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL(String.format("insert into %s values(%s);",th_name,kural_number));

    }

    public ArrayList<String> getFavouriteList(){

        sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from favourite",null);

        ArrayList<String> listOfFavKurals = new ArrayList<>();

        while (cursor.moveToNext()){
            listOfFavKurals.add(cursor.getString(0));
        }
        return listOfFavKurals;
    }

    public int getLength(){

        int count = 0;

        sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from favourite",null);

        while (cursor.moveToNext()){
            count++;
        }
        return count;
    }

}