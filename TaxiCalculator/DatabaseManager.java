package com.example.TaxiCalculator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

public class DatabaseManager  extends SQLiteOpenHelper {
    
    private static final String DATABASE_NAME = "taxicabDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TAXI = "taxicab";
    
    //column names
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String BASEPRICE = "baseprice";
    private static final String PRICE_MILE = "price_mile";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //creates table

        String sqlCreate = "create table " + TABLE_TAXI + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + BASEPRICE;
        sqlCreate += " real, " + PRICE_MILE + " real )";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_TAXI);
        // Re-create tables
        onCreate(db);

    }

    public void insert(Taxi taxi) { //insert using InsertActivity
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TAXI;
        sqlInsert += " values( null, '" + taxi.getName();
        sqlInsert += "', '" + taxi.getBasePrice();
        sqlInsert += "', '" + taxi.getPricePerMile() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Taxi> selectAll() { //to display database

        String sqlQuery = "select * from " + TABLE_TAXI;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Taxi> taxies = new ArrayList<>();
        while (cursor.moveToNext()) {
            Taxi currentTaxi = new Taxi(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
            taxies.add(currentTaxi);
        }

        db.close();
        return taxies;
    }

    public void deleteById(int id) { //delete using DeleteActivity
        //create a delete statement
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TAXI;
        sqlDelete += " where " + ID + " = " + id;

        //execute the delete statement
        db.execSQL(sqlDelete);
        db.close();
    }
}
