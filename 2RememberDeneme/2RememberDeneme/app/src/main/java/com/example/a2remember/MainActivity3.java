package com.example.a2remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    DatePicker datePick;
    Button btnhtr;
    Button btngtr;
    EditText etxtani;
    ListView listview;
    StringBuilder builder= new StringBuilder();
    int i;
    int year;
    int mount;
    int day;
    int yilh = 0;
    int ayh=0;
    int gunh=0;
    int id;
    String ani;
    List<tarih> listtarih;
    Context context = this;
    Veritabani db = new Veritabani(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        datePick = (DatePicker) findViewById(R.id.datePick);
        btnhtr   = (Button) findViewById(R.id.btnhtr);
        btngtr = (Button) findViewById(R.id.btngtr);
        etxtani  = (EditText) findViewById(R.id.etxtani);
        listview = (ListView) findViewById(R.id.listview);



        Calendar zaman = Calendar.getInstance();
        year =zaman.get(Calendar.YEAR);
        mount = zaman.get(Calendar.MONTH);
        day = zaman.get(Calendar.DAY_OF_MONTH);

        List<String> icerikler = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1,android.R.id.text1,icerikler);

        datePick.init(year, mount, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                yilh = year;
                ayh = monthOfYear+1;
                gunh = dayOfMonth;
                id = yilh+ayh+gunh;
            }
        });


        btnhtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ani = String.valueOf(etxtani.getText());

                db.ekle(new tarih(gunh,ayh,yilh,ani,id));



            }

        });

        btngtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listtarih = db.listele();
                icerikler.clear();
                for(i=0; i<listtarih.size(); i++){
                    icerikler.add(i, listtarih.get(i).getIcerik() + "           "+ listtarih.get(i).getGun()+"/"+listtarih.get(i).getAy()+
                            "/"+listtarih.get(i).getYil() );


                    //builder.append(listtarih.get(i).getIcerik()).append("   ").append(listtarih.get(i).getGun()).append("/").
                    // append(listtarih.get(i).getAy()).append("/").append(listtarih.get(i).getYil());

                }
                listview.setAdapter(adapter);
            }
        });
    }
}