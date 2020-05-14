package com.example.mobilprojem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class SQliteHelper extends SQLiteOpenHelper {
    private static final int database_VERSION = 1;
    private static final String database_NAME = "Mprojeveritabanim"; // veritabanımıza isim verdik
    private static final String table_BOOKS = "kitap";  //  oluşturulacak tablomuzun adı ve sutun adları tanımlanır.
    private static final String kitapid = "kitapid";
    private static final String kitapadi = "kitapadi";
    private static final String yazaradi = "yazaradi";
    private static final String sayfasayisi = "sayfasayisi";
    private static final String kitapturu = "kitapturu";
    private static final String ozet = "ozet";
    private static final String[] sutunlar = {kitapid, kitapadi, yazaradi, sayfasayisi, kitapturu, ozet};
    private static final String createBooksTable = "CREATE TABLE "      //oluşturmak kolay olması için String bir değişkene gerekli sql kodu yazılır.
            + table_BOOKS + " ("
            + kitapid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + kitapadi + " TEXT, "
            + yazaradi + " TEXT, "
            + sayfasayisi + " TEXT, "
            + kitapturu + " TEXT, "
            + ozet + " TEXT )";

    private static final String table_uye = "uye";   // uye tablosu
    private static final String uyeadi = "uyeismi";
    private static final String uyekullaniciadi = "uyekadi";
    private static final String uyesifre = "uyesifre";
    private static final String[] stnlar = {uyeadi, uyekullaniciadi, uyesifre};
    private static final String uyeTable = "CREATE TABLE "
            + table_uye + " ("
            + uyeadi + " TEXT, "
            + uyekullaniciadi + " TEXT, "
            + uyesifre + " TEXT )";

    private static final String table_dsoBOOKS = "dsokitaplist";  // daha sonra oku için tablo
    private static final String dsokitapid = "dsokitapid";
    private static final String dsokitapadi = "dsokitapadi";
    private static final String dsoyazaradi = "dsoyazaradi";
    private static final String dsosayfasayisi = "dsosayfasayisi";
    private static final String dsokitapturu = "dsokitapturu";
    private static final String dsoozet = "dsoozet";
    private static final String[] dsosutunlar = {dsokitapid, dsokitapadi, dsoyazaradi, dsosayfasayisi, dsokitapturu, dsoozet};
    private static final String createdsoBooksTable = "CREATE TABLE "
            + table_dsoBOOKS + " ( "
            + dsokitapid + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + dsokitapadi + " TEXT, "
            + dsoyazaradi + " TEXT, "
            + dsosayfasayisi + " TEXT, "
            + dsokitapturu + " TEXT, "
            + dsoozet + " TEXT )";

    public SQliteHelper(@Nullable Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createBooksTable);
        db.execSQL(uyeTable);
        db.execSQL(createdsoBooksTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_BOOKS);
        db.execSQL("DROP TABLE IF EXISTS " + table_uye);
        db.execSQL("DROP TABLE IF EXISTS " + table_dsoBOOKS);
        this.onCreate(db);


    }

    public void dsoKitapEkle(int gelenid) {  // daha sonra ekleme işlemi yapılınca kitaplar tablosundaki tüm kitap bilgileri dahasonraoku kitap tablosuna kaydedilir.
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO dsokitaplist ( dsokitapid, dsokitapadi, dsoyazaradi, dsosayfasayisi, dsokitapturu, dsoozet ) " +
                "SELECT kitapid, kitapadi, yazaradi, sayfasayisi, kitapturu, ozet FROM kitap WHERE kitapid = "+ gelenid);
        db.close();
    }


    public void KitapEkle(kitapBilgileri kitapBilgileri) {        //kitaplar tablosuna kitap ekleme işlemi
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues deger = new ContentValues();
        deger.put(kitapadi, kitapBilgileri.getKitapadi());
        deger.put(yazaradi, kitapBilgileri.getYazaradi());
        deger.put(sayfasayisi, kitapBilgileri.getSayfasayisi());
        deger.put(kitapturu, kitapBilgileri.getKitapturu());
        deger.put(ozet, kitapBilgileri.getOzet());
        db.insert(table_BOOKS, null, deger);
        db.close();
    }

    public List<kitapBilgileri> KitaplariGetir() {        // kaydedilmiş kitapların ekrana listelenmesi için kullanılan method.
        List<kitapBilgileri> kitaplar = new ArrayList<>();
        String sql = "SELECT * FROM " + table_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        kitapBilgileri kitap = null;
        if (cursor.moveToFirst()) {
            do {
                kitap = new kitapBilgileri();

                kitap.setKitapid(Integer.parseInt(cursor.getString(0)));
                kitap.setKitapadi(cursor.getString(1));
                kitap.setYazaradi(cursor.getString(2));
                kitap.setSayfasayisi(cursor.getString(3));
                kitap.setKitapturu(cursor.getString(4));
                kitap.setOzet(cursor.getString(5));

                kitaplar.add(kitap);
            } while (cursor.moveToNext());
        }
        return kitaplar;
    }

    public List<kitapBilgileri> dsoKitaplariGetir() {         // daha sonra oku kitaplarının ekrana listelenmesi için kullanılan method.
        List<kitapBilgileri> kitaplar = new ArrayList<>();
        String sql = "SELECT * FROM " + table_dsoBOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        kitapBilgileri kitap = null;
        if (cursor.moveToFirst()) {
            do {
                kitap = new kitapBilgileri();

                kitap.setKitapid(Integer.parseInt(cursor.getString(0)));
                kitap.setKitapadi(cursor.getString(1));
                kitap.setYazaradi(cursor.getString(2));
                kitap.setSayfasayisi(cursor.getString(3));
                kitap.setKitapturu(cursor.getString(4));
                kitap.setOzet(cursor.getString(5));

                kitaplar.add(kitap);
            } while (cursor.moveToNext());
        }
        return kitaplar;
    }

    public void uyeEkle(String kismi, String kadi, String ksifre) {  // uye kayıt işleminde kullanılan method.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues deger = new ContentValues();

        deger.put(uyeadi, kismi);
        deger.put(uyekullaniciadi, kadi);
        deger.put(uyesifre, ksifre);

        db.insert(table_uye, null, deger);
        db.close();
    }

    public kitapBilgileri ozetOku(int gelenid) {     //veritabanında kayıtlı kitap bilgilerinin id ye göre listelenmesi için kullanılan method.

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_BOOKS, sutunlar, "kitapid = ?", new String[]{String.valueOf(gelenid)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        kitapBilgileri kitap = new kitapBilgileri();
        kitap.setKitapid(Integer.parseInt(cursor.getString(0)));
        kitap.setKitapadi(cursor.getString(1));
        kitap.setYazaradi(cursor.getString(2));
        kitap.setSayfasayisi(cursor.getString(3));
        kitap.setKitapturu(cursor.getString(4));
        kitap.setOzet(cursor.getString(5));

        return kitap;

    }

    public List<kitapBilgileri> onerilenlerListele(String tur,int gelenid) {     //kitap özetlerinin altında gösterilen kitabın turu ile aynı kitapların sorgulanıp yazdırılması için method.
        List<kitapBilgileri> kitaplar = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table_BOOKS + " WHERE kitapturu = ? AND kitapid != ? ", new String[]{String.valueOf(tur), String.valueOf(gelenid)});
        kitapBilgileri kitap = null;
        if (cursor.moveToFirst()) {
            do {
                kitap = new kitapBilgileri();

                kitap.setKitapid(Integer.parseInt(cursor.getString(0)));
                kitap.setKitapadi(cursor.getString(1));
                kitap.setYazaradi(cursor.getString(2));
                kitap.setSayfasayisi(cursor.getString(3));
                kitap.setKitapturu(cursor.getString(4));
                kitap.setOzet(cursor.getString(5));

                kitaplar.add(kitap);
            } while (cursor.moveToNext());
        }
        return kitaplar;
    }

    public boolean kullaniciadiKontrol(String kadi) {      //giriş kısmında kullanıcı adının veritabınından kontrol edilmesi.

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM uye WHERE uyekadi = ?", new String[]{kadi});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public boolean sifreKontrol(String ksifre) {        //giriş kısmında kullanıcı sifresinin veritabınından kontrol edilmesi.

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM uye WHERE uyesifre = ?", new String[]{ksifre});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;

    }

    public void KitapSil(int kitapId) {    //veritabınında silme işlemi.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_BOOKS, kitapid + " = ?", new String[]{String.valueOf(kitapId)});
        db.close();

    }
    public void dsoKitapSil(int kitapId) {       //daha sonra oku veritabınında silme işlemi.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_dsoBOOKS, dsokitapid + " = ?", new String[]{String.valueOf(kitapId)});
        db.close();

    }

    public int KitapGuncelle(kitapBilgileri kitapguncelle, int gelenid) { //veritabında güncelleme işlemi.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(kitapadi, kitapguncelle.getKitapadi());
        cv.put(yazaradi, kitapguncelle.getYazaradi());
        cv.put(sayfasayisi, kitapguncelle.getSayfasayisi());
        cv.put(kitapturu, kitapguncelle.getKitapturu());
        cv.put(ozet, kitapguncelle.getOzet());
        int i = db.update(table_BOOKS, cv, kitapid + " = ?", new String[]{String.valueOf(gelenid)});
        db.close();
        return i;

    }


}