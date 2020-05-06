package com.example.wonderfulproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "taxiDB";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_TAXI = "taxi";
        private static final String BASEPRICE = "price";
        private static final String PRICEPERMILE = "price";
        private static final String ID = "id";
        private static final String NAME = "name";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //declared variables above
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override //auto-implemented
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_TAXI);
        // Re-create tables
        onCreate(db);
    }   
}
