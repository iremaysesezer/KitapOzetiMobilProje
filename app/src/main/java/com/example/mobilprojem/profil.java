package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class profil extends AppCompatActivity {

    TextView kullaniciadi;
    Button anasayfayadon, profiledon;
    String kadi;
    uyeBilgileri gelenkadi;
    Context context = this;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        tanimla();

        Bundle intent =getIntent().getExtras();
        String kullanici = intent.getString("kullaniciadi");
        kullaniciadi.setText(kullanici);
        tiklama();

    }

    public void tanimla() {

        kullaniciadi = findViewById(R.id.kullaniciadi);
        anasayfayadon = findViewById(R.id.anasayfayaDon);


    }

    public void tiklama() {

        anasayfayadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), anasayfa.class);
                startActivity(intent);
            }
        });
    }
}
