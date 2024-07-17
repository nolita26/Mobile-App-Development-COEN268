package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchCourse extends AppCompatActivity {

    private EditText et_name;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_course);

        et_name = findViewById(R.id.edt_search);
        tv_result = findViewById(R.id.tv_result);
    }

    public void search(View view) {
        String name = et_name.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "Number cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        CourseDBHelper courseDBHelper = new CourseDBHelper(this);
        SQLiteDatabase db = courseDBHelper.getWritableDatabase();

        String[] columns = {Course.Courses.COURSE_NAME, Course.Courses.PROFESSOR};
        String selection = Course.Courses.COURSE_NAME + " LIKE?";
        String[] selectionArgs = { name + "%"};

        Cursor cursor = db.query(Course.Courses.TABLE_NAME, columns,
                selection, selectionArgs, null, null, Course.Courses.COURSE_NAME);

        String result = "";
        while(cursor.moveToNext()) {
            String courseName = cursor.getString(cursor.getColumnIndex(Course.Courses.COURSE_NAME));
            String prof = cursor.getString(cursor.getColumnIndex(Course.Courses.PROFESSOR));

            // append this record to result
            result = result + "\n\n" + courseName + " " + prof;
        }
        db.close();

        if (result.isEmpty()) {
            Toast.makeText(this, "No record found", Toast.LENGTH_SHORT).show();
        }
        tv_result.setText(result);

    }
}