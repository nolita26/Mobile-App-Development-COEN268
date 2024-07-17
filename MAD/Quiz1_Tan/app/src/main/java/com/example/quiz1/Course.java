package com.example.quiz1;

import android.provider.BaseColumns;

public final class Course {
    private Course() {

    }

    public static class Courses implements BaseColumns {
        public static final String TABLE_NAME = "course_info";
        public static final String COURSE_NAME = "name";
        public static final String PROFESSOR = "professor";
    }
}
