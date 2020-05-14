package com.example.mobilprojem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class kitapozet extends AppCompatActivity {


    Log log;
    Button ozetkaydet,anasayfabtn;
    EditText kitapAdi, yazarAdi, sayfaSayisi, ozet;
    Spinner kitapTuru;
    ArrayAdapter arrayAdapter;
    String[] turler = {"Edebiyat", "Roman", "Çocuk ve Gençlik", "Araştırma-Tarih", "Bilim", "Çizgi Roman", "Din Tasavvuf", "Felsefe", "Kişisel Gelişim"};
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitapozet);
        tanimla();
       db.getWritableDatabase();
        ozetkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kkitapadi = kitapAdi.getText().toString();
                final String kyazaradi = yazarAdi.getText().toString();
                final String ksayfasayisi = sayfaSayisi.getText().toString();
                final String kozet = ozet.getText().toString();
                final String kturu = kitapTuru.getSelectedItem().toString();
                if (kkitapadi.isEmpty() || kyazaradi.isEmpty() || ksayfasayisi.isEmpty() || kozet.isEmpty() || kturu.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Boş Alanları Doldurunuz.",Toast.LENGTH_LONG).show();
                    return;
                }
                db.KitapEkle(new kitapBilgileri(kkitapadi,kyazaradi,ksayfasayisi,kturu,kozet));
                Toast.makeText(getApplicationContext(),"Kayıt Ekleme Başarılı",Toast.LENGTH_LONG).show();
                anasayfayagec();
            }
        });
        anasayfabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anasayfayagec();
            }
        });

    }
    public void tanimla() {

        kitapAdi = findViewById(R.id.EditTextKitapAdi);
        yazarAdi = findViewById(R.id.EditTextYazarAdi);
        kitapTuru = findViewById(R.id.Turu);
        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, turler);
        kitapTuru.setAdapter(arrayAdapter);

        sayfaSayisi = findViewById(R.id.EditTextSayfa);
        ozet = findViewById(R.id.EditTextOzet);
        ozetkaydet = findViewById(R.id.ozetKaydet);
        anasayfabtn = findViewById(R.id.anasayfayaDon);
    }
    public void anasayfayagec(){
        Intent intent = new Intent(getApplicationContext(),anasayfa.class);
        startActivity(intent);
    }
}