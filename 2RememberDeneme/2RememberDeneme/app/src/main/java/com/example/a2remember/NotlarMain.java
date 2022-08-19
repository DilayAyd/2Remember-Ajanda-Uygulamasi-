package com.example.a2remember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

public class NotlarMain extends AppCompatActivity {

    //Toolbar toolbar;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Not> notlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notlar_main);

 //       toolbar= findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Veritabani db = new Veritabani(this);
        notlar= db.getNotlar();

        recyclerView = findViewById(R.id.NotListesi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new Adapter(this,notlar);
        recyclerView.setAdapter(adapter);
        notlar= db.getNotlar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(/*@NonNull*/ MenuItem item) {
        if (item.getItemId()==R.id.ekle){
            Intent i = new Intent (this,NotEkle.class);
            startActivity(i);
            //Toast.makeText(this, "ekleye tıklandı", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }


    public void home_click(MenuItem item) {
        Intent i = new Intent(NotlarMain.this, MainActivity2.class);
        startActivity(i);
    }
}