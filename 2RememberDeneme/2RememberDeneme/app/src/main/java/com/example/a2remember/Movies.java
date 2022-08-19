package com.example.a2remember;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class Movies extends AppCompatActivity {

    Veritabani veritabani;
    ArrayAdapter<String> mAdapter;
    ListView filmListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        veritabani = new Veritabani(this);
        filmListesi = (ListView) findViewById(R.id.movie_list);
        FilmleriGetir();
    }

    private void FilmleriGetir() {
        ArrayList<String> itemArrayListesi = veritabani.FilmleriGetir();
        /*Adapter null olana kadar sorgu çalışıyor.*/
        if (mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.gorev_basligi, itemArrayListesi);
            filmListesi.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(itemArrayListesi);
            mAdapter.notifyDataSetChanged();
        }
    }
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
                        .setTitle("Yeni Dizi/Film")
                        /*Dialog ekranının mesajı*/
                        .setMessage("Hangi dizi/filmi izliyoruz?")
                        /*Dialog ekranında yukarıda oluşturulan EditText'i gösteriyoruz.*/
                        .setView(gorevEditText)
                        /*Olumlu cevap butonuna basılırsa (yani sağ taraftakine) yapılacak işlemler tanımlanıyor.*/
                        .setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*EditText'ten alınan değeri gorev değişkenine aktarıyoruz.*/
                                String item = String.valueOf(gorevEditText.getText());
                                /*veritabanında oluşturduğumuz yeniGorevEkle metoduna bu değeri gönderim ekleme işlemini yapıyoruz.*/
                                veritabani.yeniFilmEkle(item);
                                /*Her görev eklendiğinde ekrandaki görevler otomatik yenilensin diye tumGorevleriGetir metodunu tekrar tanımlıyoruz.*/
                                FilmleriGetir();
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
    public void gorevSil(View view){
        View parent = (View)view.getParent();
        TextView gorevEditText = (TextView)parent.findViewById(R.id.gorev_basligi);
        String item = String.valueOf(gorevEditText.getText());
        veritabani.FilmSil(item);
        FilmleriGetir();
    }
}