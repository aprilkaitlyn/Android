package com.example.todolistv1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODO = "todo";
    private static final String ID = "id";
    private static final String ITEM = "item";


    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //declared variables above
    }

    @Override //auto-implemented
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement
        String sqlCreate = "create table " + TABLE_TODO + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + ITEM + " text )" ;

        db.execSQL(sqlCreate); //executes onto SQLite
    }

    @Override //auto-implemented
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_TODO);
        // Re-create tables
        onCreate(db);
    }

    public void insert(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_TODO;
        sqlInsert += " values( null, '" + todo.getItem() + "' )";

        db.execSQL(sqlInsert); //execute insert statement
        db.close();
    }

    public ArrayList<ToDo> selectAll() { //from DeleteActivity
        String sqlQuery = "select * from " + TABLE_TODO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<ToDo> todos = new ArrayList<ToDo>();
        while(cursor.moveToNext()) {
            ToDo currentToDo = new ToDo(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1));
            todos.add(currentToDo);
        }
        db.close();
        return todos;
    }

    public void deleteById(int id) { //from DeleteActivity
       //create a delete statement
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_TODO;
        sqlDelete += " where " + ID + " = " + id;

        //execute the delete statement
        db.execSQL(sqlDelete);
        db.close();
    }

}
