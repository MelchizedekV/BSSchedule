package com.example.user.bsschedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQliteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Bsscheduler";
    public static final String TABLE_NAME = "Scheduler";
    public static final int VERSION = 2;
    public static final String USER_ID = "_id";
    public static final String CLASS_TYPE = "CLASS_TYPE";
    public static final String DATE = "DATE";
    public static final String DAY = "DAY";
    public static final String PLACE = "PLACE";
    public static final String SPEAKER = "SPEAKER";
    public static final String SUBJECT = "SUBJECT";
    public static final String TIME = "TIME";


    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            " ("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ CLASS_TYPE+" VARCHAR(255)," + DATE+" VARCHAR(225)," +DAY+" VARCHAR(255),"+ PLACE+" VARCHAR(225),"+SPEAKER+" VARCHAR(255) ,"+ SUBJECT+" VARCHAR(225),"+ TIME+" VARCHAR(225));";

    public static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;


    SQliteHelper(Context context)
    {
        super(context,DATABASE_NAME,null,VERSION);

    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // called only once when db is called for the first time

        sqLiteDatabase.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // called only once when db updated

        //version need to changed

          sqLiteDatabase.execSQL(DROP_TABLE);

          onCreate(sqLiteDatabase);


    }
}
