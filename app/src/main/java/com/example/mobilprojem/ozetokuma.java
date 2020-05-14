package com.example.mobilprojem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ozetokuma extends AppCompatActivity {

    Button anasayfayagir, dsoekle;
    String tur;
    Context context = this;
    kitapBilgileri secilenKitap;
    List<kitapBilgileri> kitapturu;
    TextView kitapadi, yazaradi, sayfasayisi, turu, ozet;
    ListView ls;
    SQliteHelper db = new SQliteHelper(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozetokuma);
        tanimla();
        final SQliteHelper db = new SQliteHelper(context);
        Intent intent = getIntent();
        final int gelenid = intent.getIntExtra("secilenkitapid",-1);
        secilenKitap = db.ozetOku(gelenid);
        kitapadi.setText(secilenKitap.getKitapadi());
        yazaradi.setText(secilenKitap.getYazaradi());
        sayfasayisi.setText(secilenKitap.getSayfasayisi());
        turu.setText(secilenKitap.getKitapturu());
        ozet.setText(secilenKitap.getOzet());

        tur = turu.getText().toString();
        kitapturu = db.onerilenlerListele( tur, secilenKitap.getKitapid() );
        List<String> listekitap = new ArrayList<>();
        for (int i = 0; i < kitapturu.size(); i++) {
            listekitap.add(i, kitapturu.get(i).getYazaradi());
        }
        ListAdapter lsa = new ArrayAdapter<>(this, R.layout.onerilenler, R.id.kitap, listekitap);
        ls.setAdapter(lsa);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ozetokuma.class);
                intent.putExtra("secilenkitapid", gelenid);
                Log.i("id", String.valueOf(kitapturu.get(position).getKitapid()));
                startActivity(intent);


            }
        });

        dsoekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.dsoKitapEkle(gelenid);
                Toast.makeText(getApplicationContext(),"Daha Sonra Oku Listesine Eklendi.",Toast.LENGTH_LONG).show();
             /*   Intent intent = new Intent(getApplicationContext(), dahasonraoku.class);
                intent.putExtra("dsokitapid",secilenKitap.getKitapid());
                Log.i("dsoid", String.valueOf(secilenKitap.getKitapid()));
                startActivity(intent);*/
            }
        });
                tiklama();
    }

    public void tanimla() {

        kitapadi = findViewById(R.id.kitapadi);
        yazaradi = findViewById(R.id.yazaradi);
        sayfasayisi = findViewById(R.id.sayfasayisi);
        turu = findViewById(R.id.kitapturu);
        ozet = findViewById(R.id.ozet);
        anasayfayagir = findViewById(R.id.anasayfayaDon);
        ls = findViewById(R.id.onerilenler);
        dsoekle = findViewById(R.id.dsoekle);

    }

    public void tiklama() {

        anasayfayagir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), anasayfa.class);
                startActivity(intent);
            }
        });
     /*   dsoekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dahasonraoku.class);
                intent.putExtra("dsokitapid",secilenKitap.getKitapid());
                Log.i("dsoid", String.valueOf(secilenKitap.getKitapid()));
                startActivity(intent);
            }
        });*/

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ozetokuma.class);
                intent.putExtra("secilenkitapid", kitapturu.get(position).getKitapid());
                Log.i("id", String.valueOf(kitapturu.get(position).getKitapid()));
                startActivity(intent);
            }
        });
    }
}
