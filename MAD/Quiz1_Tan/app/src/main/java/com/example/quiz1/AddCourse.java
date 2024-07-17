package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourse extends AppCompatActivity {

    private EditText et_name, et_prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        et_name = findViewById(R.id.edt_course);
        et_prof = findViewById(R.id.edt_prof);
    }

    public void Add(View view) {
        String course = et_name.getText().toString();
        String prof = et_prof.getText().toString();

        if (course.isEmpty() || prof.isEmpty()) {
            Toast.makeText(this, "course and professor cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        CourseDBHelper courseDBHelper = new CourseDBHelper(this);
        SQLiteDatabase check = courseDBHelper.getReadableDatabase();
        Cursor cursor = check.query(Course.Courses.TABLE_NAME, null, null,
                null, null, null, Course.Courses.COURSE_NAME);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Course.Courses.COURSE_NAME));
            String professor = cursor.getString(cursor.getColumnIndex(Course.Courses.PROFESSOR));

            if (name.equals(course) && professor.equals(prof)) {
                Toast.makeText(this, "Duplicate", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        SQLiteDatabase db = courseDBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Course.Courses.COURSE_NAME, course);
        contentValues.put(Course.Courses.PROFESSOR, prof);

        long recordID = db.insert(Course.Courses.TABLE_NAME, null, contentValues);
        db.close();

        if (recordID == -1) {
            Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Successfully added course " + course + " in database", Toast.LENGTH_SHORT).show();
        }

        et_name.setText("");
        et_prof.setText("");

    }

    public void show(View view) {
        Intent intent = new Intent(this, ShowAll.class);
        startActivity(intent);
    }

    public void search_name(View view) {
        Intent intent = new Intent(this, SearchProf.class);
        startActivity(intent);
    }

    public void search_number(View view) {
        Intent intent = new Intent(this, SearchCourse.class);
        startActivity(intent);
    }
}