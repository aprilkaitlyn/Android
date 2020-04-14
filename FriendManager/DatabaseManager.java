package com.example.friendmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "friendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIEND = "friend";
    private static final String ID = "id";
    private static final String FIRSTNAME = "first"; //names of database columns
    private static final String LASTNAME = "last";
    private static final String EMAIL = "email";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //declared variables above
    }

    @Override //auto-implemented
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement
        String sqlCreate = "create table " + TABLE_FRIEND + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + FIRSTNAME;
        sqlCreate += " text, " + LASTNAME;
        sqlCreate += " text, " + EMAIL + " text )" ;

        db.execSQL(sqlCreate); //executes onto SQLite??
    }

    @Override //auto-implemented
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_FRIEND);
        // Re-create tables
        onCreate(db);
    }

    public void insert(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIEND;
        sqlInsert += " values( null, '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName();
        sqlInsert += "', '" + friend.getEmail() + "' )";

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friend> selectAll() { //from DeleteActivity  
        String sqlQuery = "select * from " + TABLE_FRIEND;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friend> friends = new ArrayList<Friend>();
        while(cursor.moveToNext()) {
            Friend currentFriend = new Friend(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));
            friends.add(currentFriend);
        }
        db.close();
        return friends;
    }

    public void deleteById(int id) { //from DeleteActivity
       //create a delete statement
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIEND;
        sqlDelete += " where " + ID + " = " + id;

        //execute the delete statement
        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String firstname, String lastname, String email) { //create SQL update statement
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_FRIEND;
        sqlUpdate += " set " + FIRSTNAME + " = '" + firstname + "', ";
        sqlUpdate += LASTNAME + " = '" + lastname + "', ";
        sqlUpdate += EMAIL + " = '" + email + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }
}
