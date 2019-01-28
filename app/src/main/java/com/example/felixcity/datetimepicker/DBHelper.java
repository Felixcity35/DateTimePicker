package com.example.felixcity.datetimepicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static  String db_Name = "date.db";
    public static  int db_version = 1;
    public static  String table_name = "datePicker";
    public static  String Column_1 = "ID";
    public static  String Column_2 = "Date";
    public static  String Column_3 = "Time";

    public DBHelper(Context context) {
        super(context, db_Name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ table_name + "( ID INTEGER PRIMARY KEY AUTOINCREMENT ,Date TEXT NOT NULL, Time TEXT NOT NULL)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ table_name);
        onCreate(db);
    }
}
