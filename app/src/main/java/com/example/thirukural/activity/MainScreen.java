package com.example.thirukural.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.thirukural.R;

public class MainScreen extends AppCompatActivity {

    public static String kural_number;
    public static boolean from_saved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
}