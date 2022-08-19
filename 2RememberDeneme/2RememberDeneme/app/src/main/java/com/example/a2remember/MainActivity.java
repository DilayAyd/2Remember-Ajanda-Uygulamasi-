package com.example.a2remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent i = new Intent(MainActivity.this,ToDo.class);
        startActivity(i);

    }

    public void shopClick(View view) {
    Intent i = new Intent(MainActivity.this,ShoppingList.class);
    startActivity(i);
    }

    public void kitapClick(View view) {
        Intent i = new Intent(MainActivity.this,Books.class);
        startActivity(i);
    }

    public void musicClick(View view) {
        Intent i = new Intent(MainActivity.this, Musics.class);
        startActivity(i);
    }

    public void filmClick(View view) {
        Intent i = new Intent(MainActivity.this, Movies.class);
        startActivity(i);
    }

    public void mekanClick(View view) {
        Intent i = new Intent(MainActivity.this, Places.class);
        startActivity(i);
    }
}