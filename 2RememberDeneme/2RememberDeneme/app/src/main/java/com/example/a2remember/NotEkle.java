package com.example.a2remember;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NotEkle extends AppCompatActivity {

   // Toolbar toolbar;
    EditText notBaslik, notIcerik;
    Calendar c;
    String bugunTarih;
    String suanZaman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ekle);

      //  toolbar= findViewById(R.id.toolbar);
       // toolbar.setTitleTextColor(getResources().getColor(R.color.white));
       // setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Yeni Not");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notBaslik=findViewById(R.id.notBaslik);
        notIcerik=findViewById(R.id.notIcerik);

        notBaslik.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()!= 0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // gün ve zamanı alma
        c= Calendar.getInstance();
        bugunTarih = c.get(Calendar.YEAR)+"/"+ (c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);
        suanZaman= pad (c.get(Calendar.HOUR))+":"+pad (c.get(Calendar.MINUTE));

        Log.d("calender", " Tarih ve Zaman:"+bugunTarih+ "ve"+suanZaman);

    }

    private String pad(int i) {
        if (i<10)
            return "0"+i;
        return String.valueOf(i) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(/*@NonNull*/ MenuItem item) {
        if (item.getItemId()==R.id.sil){

            Toast.makeText(this, "silindi", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

        if (item.getItemId()==R.id.kaydet){
            Not not = new Not(notBaslik.getText().toString(), notIcerik.getText().toString(),bugunTarih, suanZaman);
            Veritabani db =new Veritabani(this);
            db.notEkle(not);
            Toast.makeText(this, "kaydedildi", Toast.LENGTH_SHORT).show();
            //onBackPressed(); ile kaydettikten sonra geri yolluyorduk
            //goToMain(); ile main sayfaya yollayacağız ama birleştirirken notlar sayfasına göndermeyyi unutma

            goToMain();
            db.getNotlar();
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {

        // intent in gideceği yeri notlar sayfası olarak ayarlamayı unutma
        Intent i= new Intent(this,NotlarMain.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }



}