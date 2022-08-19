package com.example.a2remember;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/*SQLiteOpenHelper'dan kalıtalım.*/
public class Veritabani extends SQLiteOpenHelper {

    /*Veritabanı için ad, tablo adı, versiyon ve sütun için değişken oluşturalım.*/
    private static final String DB_NAME="Veritabani";
    private static final int DB_VER = 1;
    public static final String DB_TABLE = "Gorev";
    public static final String DB_SUTUN = "GorevIsmi";
    public static final String DB_TABLE2 = "Shopping";
    public static final String DB_SUTUN2 = "Item";
    public static final String DB_TABLE3 = "Books";
    public static final String DB_SUTUN3 = "book";
    public static final String DB_TABLE4 = "Musics";
    public static final String DB_SUTUN4 = "music";
    public static final String DB_TABLE5 = "Movies";
    public static final String DB_SUTUN5 = "movie";
    public static final String DB_TABLE6 = "Places";
    public static final String DB_SUTUN6 = "place";
    private static final String table_TARIH = "tarih";
    private static final String tarih_ID = "id";
    private static final String tarih_GUN = "gun";
    private static final String tarih_AY = "ay";
    private static final String tarih_YIL = "yil";
    private static final String tarih_ICERIK = "icerik";
    private static final String CREATE_TABLE_TARIH = "CREATE TABLE "
            + table_TARIH+" ("
            + tarih_ID+" INTEGER, "
            + tarih_GUN+" INTEGER, "
            + tarih_AY+" INTEGER, "
            + tarih_YIL+" INTEGER, "
            + tarih_ICERIK+" TEXT )";

    private static final String TABLE_NAME = "NotTbl";


    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "baslik";
    private static final String KEY_CONTENT = "icerik";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";


    public Veritabani(Context context) {
        /*Yukarıda tanımlanan değişkenleri kullanalım*/
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Tablo oluşturmak için sorgumuzu yazalım. %s olan yerlere virgülden sonra yazılan değişkenlerin geleceği belirtiliyor.*/
        String sorgu = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE, DB_SUTUN);
        String sorgu2 = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE2,DB_SUTUN2);
        String sorgu3 = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE3,DB_SUTUN3);
        String sorgu4 = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE4,DB_SUTUN4);
        String sorgu5 = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE5,DB_SUTUN5);
        String sorgu6 = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);", DB_TABLE6,DB_SUTUN6);
        /*SQL kodu çalıştırılıyor.*/
        String query = "CREATE TABLE "+TABLE_NAME+" ("+
                KEY_ID+" INTEGER PRIMARY KEY,"+
                KEY_TITLE+" TEXT,"+
                KEY_CONTENT+" TEXT,"+
                KEY_DATE+" TEXT,"+
                KEY_TIME+" TEXT"
                +" )";
        db.execSQL(query);
        db.execSQL(sorgu);
        db.execSQL(sorgu2);
        db.execSQL(sorgu3);
        db.execSQL(sorgu4);
        db.execSQL(sorgu5);
        db.execSQL(sorgu6);
        db.execSQL(CREATE_TABLE_TARIH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Veritabanında oluşan her değişiklik için veritabanında kayıtlı olan tabloyu silip yerine yenisini oluşturarak güncelleme işlemini yapıyor.*/
        String sorgu = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
        String sorgu2 = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE2);
        String sorgu3 = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE3);
        String sorgu4 = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE4);
        String sorgu5 = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE5);
        String sorgu6 = String.format("DELETE TABLE IF EXISTS %s",DB_TABLE6);
        String sorgu7 = String.format("DELETE TABLE IF EXISTS %s", table_TARIH);
        /*Yazılan sorguyu uyguluyor.*/
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL(sorgu);
        db.execSQL(sorgu2);
        db.execSQL(sorgu3);
        db.execSQL(sorgu4);
        db.execSQL(sorgu5);
        db.execSQL(sorgu6);
        db.execSQL(sorgu7);
        /*onCreate'e göndererek tabloyu oluşturuyor.*/
        onCreate(db);
    }

    public void yeniGorevEkle(String gorev){
        /*getWritableDatabase ile yazılabilir/okunabilir bağlantı oluşturuluyor.*/
        SQLiteDatabase db = this.getWritableDatabase();
        /*Veritabanı işlemleri için ContentValues oluşturuluyor.*/
        ContentValues values = new ContentValues();
        /*MainActivity'den gelen gorev değerini alarak veritabanına kaydediyoruz.*/
        values.put(DB_SUTUN, gorev);
        /*Veritabanına satır eklemek için kullanılan kod.*/
        db.insertWithOnConflict(DB_TABLE,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        /*Veritabanı bağlantısı kapatılıyor.*/
        db.close();
    }
    public void yeniItemEkle(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUTUN2, item);
        db.insertWithOnConflict(DB_TABLE2,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    public void yeniKitapEkle(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUTUN3, item);
        db.insertWithOnConflict(DB_TABLE3,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    public void yeniMuzikEkle(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUTUN4, item);
        db.insertWithOnConflict(DB_TABLE4,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    public void yeniFilmEkle(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUTUN5, item);
        db.insertWithOnConflict(DB_TABLE5,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }
    public void yeniMekanEkle(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_SUTUN6, item);
        db.insertWithOnConflict(DB_TABLE6,null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void ekle(tarih t){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues degerler = new ContentValues();
        degerler.put(tarih_ID, t.getId());
        degerler.put(tarih_GUN, t.getGun());
        degerler.put(tarih_AY, t.getAy());
        degerler.put(tarih_YIL, t.getYil());
        degerler.put(tarih_ICERIK, t.getIcerik());
        db.insert(table_TARIH,null,degerler);
        db.close();
    }

    public long notEkle (Not not){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE,not.getBaslik());
        c.put(KEY_CONTENT,not.getContent());
        c.put(KEY_DATE,not.getDate());
        c.put(KEY_TIME,not.getTime());

        long ID = db.insert(TABLE_NAME,null,c);
        Log.d("Inserted", "ID -> "+ID);
        return ID;
    }

    public void gorevSil(String gorev){
        /*getWritableDatabase ile yazılabilir/okunabilir veritabanı bağlantısı oluşturuluyor.*/
        SQLiteDatabase db = this.getWritableDatabase();
        /*MainActivity'den gelen gorev değerini silmek için aşağıdaki sorgu yazılıyor.*/
        db.delete(DB_TABLE,DB_SUTUN + " = ?", new String[]{gorev});
        /*Veritabanı bağlantısı kapatılıyor.*/
        db.close();
    }
    public void itemSil(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE2,DB_SUTUN2 + " = ?", new String[]{item});
        db.close();
    }
    public void KitapSil(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE3,DB_SUTUN3 + " = ?", new String[]{item});
        db.close();
    }
    public void MuzikSil(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE4,DB_SUTUN4 + " = ?", new String[]{item});
        db.close();
    }
    public void FilmSil(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE5,DB_SUTUN5 + " = ?", new String[]{item});
        db.close();
    }
    public void MekanSil(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE6,DB_SUTUN6 + " = ?", new String[]{item});
        db.close();
    }

    /*Veritabanına kaydedilen tüm verileri çağırmak için aşağıdaki metot kullanılıyor. Tüm verileri ArrayList içine kaydediyor.*/
    public ArrayList<String> tumGorevleriGetir(){
        /*Bir tane ArrayList listesi oluşturuluyor.*/
        ArrayList<String> gorevListesi = new ArrayList<>();
        /*getReadableDatabase ile sadece okunabilir bir veritabanı bağlantısı oluşturuluyor.*/
        SQLiteDatabase db = this.getReadableDatabase();
        /*Cursor ile sorgumuzu yapıyoruz. Tablonun içindeki tüm sütunları cursor'a aktarıyoruz.*/
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_SUTUN},null,null,null,null,null);
        /*Sonraki kayıt olmayana kadar dönmeye devam ediyor.*/
        while(cursor.moveToNext()){
            /*Her sütunun sırasını alıyor...*/
            int index = cursor.getColumnIndex(DB_SUTUN);
            /*...gorevListesi listesine aktarıyor.*/
            gorevListesi.add(cursor.getString(index));
        }
        /*cursor ve veritabanı kapatılıyor. gorevListesi döndürülüyor.*/
        cursor.close();
        db.close();
        return gorevListesi;
    }
    public ArrayList<String> tumItemleriGetir(){
        ArrayList<String> itemListesi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE2, new String[]{DB_SUTUN2},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_SUTUN2);
            itemListesi.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return itemListesi;
    }
    public ArrayList<String> tumKitaplariGetir(){
        ArrayList<String> kitapListesi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE3, new String[]{DB_SUTUN3},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_SUTUN3);
            kitapListesi.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return kitapListesi;
    }
    public ArrayList<String> MuzikleriGetir(){
        ArrayList<String> muzikListesi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE4, new String[]{DB_SUTUN4},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_SUTUN4);
            muzikListesi.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return muzikListesi;
    }
    public ArrayList<String> FilmleriGetir(){
        ArrayList<String> filmListesi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE5, new String[]{DB_SUTUN5},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_SUTUN5);
            filmListesi.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return filmListesi;
    }
    public ArrayList<String> MekanGetir(){
        ArrayList<String> mekanListesi = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE6, new String[]{DB_SUTUN6},null,null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_SUTUN6);
            mekanListesi.add(cursor.getString(index));
        }
        cursor.close();
        db.close();
        return mekanListesi;
    }
    public Not getNot(long id){
        //id=1

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[] {KEY_ID, KEY_TITLE,KEY_CONTENT,KEY_DATE,KEY_TIME},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor!= null)
            cursor.moveToFirst();

        return new Not(cursor.getLong(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
    }
    public List<Not> getNotlar() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<Not> tumNotlar= new ArrayList<>();
        //tüm kolon

        String query= " SELECT * FROM "+ TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Not not= new Not();
                not.setID(cursor.getLong(0));
                not.setBaslik(cursor.getString(1));
                not.setContent(cursor.getString(2));
                not.setDate(cursor.getString(3));
                not.setTime(cursor.getString(4));

                tumNotlar.add(not);
            }while (cursor.moveToNext());
        }
        return tumNotlar;

    }
    public List<tarih> listele(){
        List<tarih> tarihList = new ArrayList<>();
        String query = "SELECT * FROM "+table_TARIH;
        SQLiteDatabase db = this.getReadableDatabase();
        tarih tr= null;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()){
            do{
                tr = new tarih();
                tr.setId(Integer.parseInt(cursor.getString(0)));
                tr.setGun(Integer.parseInt(cursor.getString(1)));
                tr.setAy(Integer.parseInt(cursor.getString(2)));
                tr.setYil(Integer.parseInt(cursor.getString(3)));
                tr.setIcerik(cursor.getString(4));
                tarihList.add(tr);
            }while (cursor.moveToNext());
        }
        return tarihList;
    }
    public int editNot(Not not){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ not.getBaslik() + "\n ID -> "+not.getID());
        c.put(KEY_TITLE,not.getBaslik());
        c.put(KEY_CONTENT,not.getContent());
        c.put(KEY_DATE,not.getDate());
        c.put(KEY_TIME,not.getTime());
        return db.update(TABLE_NAME,c,KEY_ID+"=?",new String[]{String.valueOf(not.getID())});
    }


    void deleteNot(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }


}