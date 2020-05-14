package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText kullaniciadi, kullanicisifre;
    Button girisbutonu;
    Button uyeolbutonu;
    String kadi, ksifre;
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // db();
        db.getReadableDatabase();
        tanimla();
        tiklama();

    }

    public void tanimla() {

        kullaniciadi = findViewById(R.id.editTextKullanici);
        kullanicisifre = findViewById(R.id.editTextSifre);
        girisbutonu = findViewById(R.id.girisButon);
        uyeolbutonu = findViewById(R.id.uyeOl);

    }

    public void tiklama() {

        girisbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kadi = kullaniciadi.getText().toString();
                //db.uyeGoster(kadi);
                ksifre = kullanicisifre.getText().toString();
                if (kadi.isEmpty() || ksifre.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Boş Alanları Doldurunuz.", Toast.LENGTH_LONG).show();
                    return;
                }
                Boolean chkkadi = db.kullaniciadiKontrol(kadi);
                Boolean chkksifre = db.sifreKontrol(ksifre);
                if (chkkadi == true && chkksifre == true) {
                    Toast.makeText(getApplicationContext(), "Giriş Başarılı.", Toast.LENGTH_LONG).show();
                    profilegec();
                } else
                    Toast.makeText(getApplicationContext(), "Giriş Başarısız. Tekrar Deneyin.", Toast.LENGTH_LONG).show();
            }

        });
        uyeolbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uyekayitagec();

            }
        });

    }

    public void profilegec() {
        Intent intent = new Intent(this, profil.class);
        intent.putExtra("kullaniciadi", kullaniciadi.getText().toString());
        startActivity(intent);
    }

    public void uyekayitagec() {
        Intent intent = new Intent(this, uyeol.class);
        startActivity(intent);
    }

}
