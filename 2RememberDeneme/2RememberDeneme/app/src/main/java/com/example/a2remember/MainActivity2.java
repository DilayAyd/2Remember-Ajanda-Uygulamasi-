package com.example.a2remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void btn_list_click(View view) {
        Intent i = new Intent(MainActivity2.this, MainActivity.class);
        startActivity(i);
    }

    public void btn_weather_click(View view) {
        Intent i = new Intent(MainActivity2.this, WeatherMain.class);
        startActivity(i);
    }

    public void btn_takvim_click(View view) {
        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        startActivity(i);
    }

    public void btn_not_click(View view) {
        Intent i = new Intent(MainActivity2.this, NotlarMain.class);
        startActivity(i);
    }
}