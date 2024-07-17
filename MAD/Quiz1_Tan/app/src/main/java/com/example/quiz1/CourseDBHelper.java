package com.example.quiz1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CourseDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "courses.db";
    private static final int DATABASE_VERSION = 2;

    private static final String CREATE_TABLE = "CREATE TABLE " +
            Course.Courses.TABLE_NAME + "(" +
            Course.Courses._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Course.Courses.COURSE_NAME + " TEXT NOT NULL, " +
            Course.Courses.PROFESSOR + " TEXT NOT NULL " + ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + Course.Courses.TABLE_NAME;

    public CourseDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
