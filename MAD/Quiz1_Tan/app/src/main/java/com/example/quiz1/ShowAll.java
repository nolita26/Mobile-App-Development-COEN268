package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ShowAll extends AppCompatActivity {

    private TextView tv_all_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        tv_all_course = findViewById(R.id.tv_all_course);
        getAllCourses();
    }

    public void getAllCourses() {
        CourseDBHelper courseDBHelper = new CourseDBHelper(this);
        SQLiteDatabase db = courseDBHelper.getReadableDatabase();

        Cursor cursor = db.query(Course.Courses.TABLE_NAME, null, null,
                null, null, null, Course.Courses.COURSE_NAME);

        String result = "";
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Course.Courses.COURSE_NAME));
            String prof = cursor.getString(cursor.getColumnIndex(Course.Courses.PROFESSOR));

            // append this record to result
            result = result + "\n\nCourse: " + name + "\nProfessor: " + prof;
        }
        db.close();
        tv_all_course.setText(result);
    }
}