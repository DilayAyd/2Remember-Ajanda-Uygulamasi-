package com.example.a2remember;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a2remember.R;
import com.example.a2remember.Veritabani;

import java.util.ArrayList;

public class ToDo extends AppCompatActivity {

    /*Veritabani nesnesi, adapter ve listView tanımlayalım.*/
    Veritabani veritabani;
    ArrayAdapter<String> mAdapter;
    ListView gorevListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        /*Veritabani nesnesi oluşturalım.*/
        veritabani = new Veritabani(this);
        /*Görevlerin yazılacağı listView bağlantısını yapalım. gorevListView, activity_main.xml'de tanımlanmıştı.*/
        gorevListesi = (ListView) findViewById(R.id.gorevListView);
        /*Tüm görevleri getirmesi için kullanacağımız metot.*/
        tumGorevleriGetir();
    }

    private void tumGorevleriGetir() {
        /*Veritabanı sınıfında yazılan tumGorevleriGetir metodundan kayıtların hepsini listeye aktardık.*/
        ArrayList<String> gorevArrayListesi = veritabani.tumGorevleriGetir();
        /*Adapter null olana kadar sorgu çalışıyor.*/
        if (mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.gorev_basligi, gorevArrayListesi);
            gorevListesi.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(gorevArrayListesi);
            mAdapter.notifyDataSetChanged();
        }
    }

    /*menu.xml'li tanımlamak için onCreateOptionMenu'yü Override edelim.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*xml'i bağlayalım.*/
        getMenuInflater().inflate(R.menu.menu,menu);
        /*İkon rengini beyaz yapalım. return hariç alttaki kodları yapmasanızda olur.*/
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    /*Menüden seçilen elemanlar için yapılacak işlemleri onOptionsItemSelected içerisinde belirtiyoruz. Yani ekranda + tuşuna basınca yapılacakları belirteceğiz.*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*Menüde tıklanan itemin id değerini alıyor.*/
        switch (item.getItemId()){
            /*Alınan id değeri yeni_gorev_ekle ise alttaki işlemleri yapacak.*/
            case R.id.yeni_gorev_ekle:
                /*Yeni bir tane EditText oluşturuyor.*/
                final EditText gorevEditText = new EditText(this);
                /*Dialog ekranı oluşturuluyor.*/
                AlertDialog dialog = new AlertDialog.Builder(this)
                        /*Dialog ekranının başlığı:*/
                        .setTitle("Yeni Görev Ekle")
                        /*Dialog ekranının mesajı*/
                        .setMessage("Ne yapmak istiyorsun?")
                        /*Dialog ekranında yukarıda oluşturulan EditText'i gösteriyoruz.*/
                        .setView(gorevEditText)
                        /*Olumlu cevap butonuna basılırsa (yani sağ taraftakine) yapılacak işlemler tanımlanıyor.*/
                        .setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*EditText'ten alınan değeri gorev değişkenine aktarıyoruz.*/
                                String gorev = String.valueOf(gorevEditText.getText());
                                /*veritabanında oluşturduğumuz yeniGorevEkle metoduna bu değeri gönderim ekleme işlemini yapıyoruz.*/
                                veritabani.yeniGorevEkle(gorev);
                                /*Her görev eklendiğinde ekrandaki görevler otomatik yenilensin diye tumGorevleriGetir metodunu tekrar tanımlıyoruz.*/
                                tumGorevleriGetir();
                            }
                        })
                        /*Olumsuz butona yani soldakine basılırsa ekranı kapatıyor.*/
                        .setNegativeButton("Vazgeç",null)
                        .create();
                /*Dialog ekranını gösteriyoruz.*/
                dialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*Sil butonuna basılınca yapılacak olan işlemleri yapıyoruz.*/
    public void gorevSil(View view){
        View parent = (View)view.getParent();
        TextView gorevEditText = (TextView)parent.findViewById(R.id.gorev_basligi);
        String gorev = String.valueOf(gorevEditText.getText());
        veritabani.gorevSil(gorev);
        tumGorevleriGetir();
    }
}