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

public class Edit extends AppCompatActivity {
   // Toolbar toolbar;
    EditText notBaslik, notIcerik;
    Calendar c;
    String bugunTarih;
    String suanZaman;
    //kullanılabilsin diye public tanımladık
    Veritabani db;
    Not not;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent i = getIntent();
        //editlemek istediğimiz notun id sini tutar
        Long id = i.getLongExtra("ID",0);
        db= new Veritabani(this);
        not= db.getNot(id);



        //toolbar= findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.white));
      //  setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(not.getBaslik());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(not.getBaslik());
        notBaslik=findViewById(R.id.notBaslik);
        notIcerik=findViewById(R.id.notIcerik);

        //edit sayfasına varolan veriyi aktarıyoruz
        notBaslik.setText(not.getBaslik());
        notIcerik.setText(not.getContent());

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
            if (notBaslik.getText().length()!=0) {
                not.setBaslik(notBaslik.getText().toString());
                not.setContent(notIcerik.getText().toString());
                //editnot metodu int döndürdüğü için
                int id= db.editNot(not);
                if (id==not.getID()){
                    Toast.makeText(this, "Not Güncellendi", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(this, "Güncelleme Başarısız!", Toast.LENGTH_SHORT).show();
                }
                goToMain();
                //startActivity(new Intent(getApplicationContext(),Detay.class));
                // Intent i= new Intent(getApplicationContext(),Detay.class);
                //i.putExtra("ID",not.getID());
                //startActivity(i);
            }
            //Not not = new Not(notBaslik.getText().toString(), notIcerik.getText().toString(),bugunTarih, suanZaman);
            //NotDatabase db =new NotDatabase(this);
            //db.notEkle(not);
            //Toast.makeText(this, "Not Güncellendi", Toast.LENGTH_SHORT).show();
            //onBackPressed(); ile kaydettikten sonra geri yolluyorduk
            //goToMain(); ile main sayfaya yollayacağız ama birleştirirken notlar sayfasına göndermeyyi unutma
            //goToMain();
        }




        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {

        // intent in gideceği yeri notlar sayfası olarak ayarlamayı unutma
        Intent i= new Intent(Edit.this,NotlarMain.class);
        startActivity(i);
    }
}