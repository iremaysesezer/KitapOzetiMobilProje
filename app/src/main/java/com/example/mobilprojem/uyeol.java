package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class uyeol extends AppCompatActivity {

    EditText kullaniciismi, kullaniciadi, kullanicisifre;
    Button kaydolbutonu;
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uyeol);
        tanimla();
        db.getWritableDatabase();
        kaydolbutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kismi = kullaniciismi.getText().toString();
                String kadi = kullaniciadi.getText().toString();
                String ksifre = kullanicisifre.getText().toString();
                if (kismi.isEmpty() || kadi.isEmpty() || ksifre.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Boş Alanları Doldurunuz.",Toast.LENGTH_LONG).show();
                    return;
                }
                SQliteHelper db = new SQliteHelper(getApplicationContext());
                db.uyeEkle(kismi,kadi,ksifre);
                Toast.makeText(getApplicationContext(),"Uye Kayıt İşlemi Başarılı.",Toast.LENGTH_LONG).show();
                girisegec();
            }
        });
    }

    public void tanimla() {

        kullaniciismi = findViewById(R.id.EditTextAdi);
        kullaniciadi = findViewById(R.id.EditTextKullaniciAdi);
        kullanicisifre = findViewById(R.id.EditTextSifre);
        kaydolbutonu = findViewById(R.id.Kaydol);

    }

    private void girisegec() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
