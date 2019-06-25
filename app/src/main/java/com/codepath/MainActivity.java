package com.codepath;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //fyi: declare fields here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //fyi: add methods here
}

//@string/my_string refs string in res/values/string.xml
//@drawable/cool_image refs img in res/drawable
//eg: System.out.println(R.string.my_string);

//methods: onCreate, onPause, onResume