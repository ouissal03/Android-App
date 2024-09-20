package com.example.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "myapp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE Users (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, login TEXT UNIQUE, password TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void addUser(User user) {
        ContentValues cv = new ContentValues();

        cv.put("username", user.getName());
        cv.put("login", user.getLogin());
        cv.put("password", user.getPassword());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert("Users", null, cv);
    }

    public boolean verifyUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM Users WHERE login='" + login + "' AND password='" + password + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) return false;
        // Close resources
        cursor.close();
        db.close();
        return true;
    }

}
