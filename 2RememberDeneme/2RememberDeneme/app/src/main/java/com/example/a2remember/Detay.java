package com.example.a2remember;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Detay extends AppCompatActivity {

    TextView mDetay;
    Veritabani db;
    Not not;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDetay= findViewById(R.id.notDetaylari);

        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);

        db = new Veritabani(this);
        not = db.getNot(id);
        //başlığı bara yazdırma
        getSupportActionBar().setTitle(not.getBaslik());
        //notun içeriğini yazdırma
        mDetay.setText(not.getContent());

        Toast.makeText(this, "baslik ->"+not.getBaslik(), Toast.LENGTH_SHORT).show();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteNot(not.getID());
                Toast.makeText(getApplicationContext(), "NOT SİLİNDİ", Toast.LENGTH_SHORT).show();
                ///////////////////////////////////////////////////////////////////////////////
                /////intent i doğru yere yönlendir
                startActivity(new Intent(getApplicationContext(),NotlarMain.class));
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(/*@NonNull*/ MenuItem item) {
        if (item.getItemId()==R.id.editNote){

            //edit activity e gönder





           // Toast.makeText(this, "düzenlendi", Toast.LENGTH_SHORT).show();
            Intent i= new Intent(this, Edit.class);
            i.putExtra("ID",not.getID());
            startActivity(i);
            //onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}