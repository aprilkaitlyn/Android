package com.example.todolistv2;

import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ToDo {

    private int id;
    private String item;
    public String duedate;


    public ToDo(int newId, String newItem, String newDueDate) {
        setID(newId);
        setItem(newItem);
        setDueDate(newDueDate);
    }

    private void setID(int newId) {
        id = newId;
    }

    private void setItem(String newItem) {
        item = newItem;
    }

    private void setDueDate(String newDueDate) { duedate = newDueDate;}

    public int getId() { return id; }

    public String getItem() {
        return item;
    }

    public String getDueDate() {return duedate;}

    public String toString() { return item; }

}
