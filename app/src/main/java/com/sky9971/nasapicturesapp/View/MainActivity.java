package com.sky9971.nasapicturesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sky9971.nasapicturesapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
}