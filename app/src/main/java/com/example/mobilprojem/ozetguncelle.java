package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ozetguncelle extends AppCompatActivity {

    Log log;
    Button ozetGuncelle,anasayfabtn,profilbtn;
    EditText kitapAdi, yazarAdi, sayfaSayisi, ozet;
    Spinner kitapTuru;
    ArrayAdapter arrayAdapter;
    kitapBilgileri secilenKitap;
    String[] turler = {"Edebiyat", "Roman", "Çocuk ve Gençlik", "Araştırma-Tarih", "Bilim", "Çizgi Roman", "Din Tasavvuf", "Felsefe", "Kişisel Gelişim"};
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozetguncelle);
        tanimla();
        db.getWritableDatabase();

        Intent intent = getIntent();
        final int gelenid = intent.getIntExtra("secilenkitapid",-1);
        secilenKitap = db.ozetOku(gelenid);
        kitapAdi.setText(secilenKitap.getKitapadi());
        yazarAdi.setText(secilenKitap.getYazaradi());
        sayfaSayisi.setText(secilenKitap.getSayfasayisi());
        // kitapTuru.setText(secilenKitap.getKitapturu());
        ozet.setText(secilenKitap.getOzet());


        ozetGuncelle.setOnClickListener(new View.OnClickListener() {
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
                db.KitapGuncelle(new kitapBilgileri(kkitapadi,kyazaradi,ksayfasayisi,kturu,kozet),gelenid);
                Toast.makeText(getApplicationContext(),"Kayıt Güncelleme Başarılı",Toast.LENGTH_LONG).show();
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
        ozetGuncelle = findViewById(R.id.ozetGuncelle);
        anasayfabtn = findViewById(R.id.anasayfayaDon);
    }
    public void anasayfayagec(){
                Intent intent = new Intent(getApplicationContext(), anasayfa.class);
                startActivity(intent);
            }
    }

